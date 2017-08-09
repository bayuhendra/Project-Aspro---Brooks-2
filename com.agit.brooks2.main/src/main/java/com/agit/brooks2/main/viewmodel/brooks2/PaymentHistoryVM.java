package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.PaymentHistoryService;
import com.agit.brooks2.common.dto.core.PaymentHistoryDTO;
import com.agit.brooks2.common.dto.core.PaymentHistoryDTOBuilder;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.status.StatusPayment;
import com.agit.brooks2.shared.zul.CommonViewModel;
import static com.agit.brooks2.shared.zul.CommonViewModel.showInformationMessagebox;
import com.agit.brooks2.shared.zul.PageNavigation;
import com.agit.brooks2.util.CommonUtil;
import com.agit.brooks2.util.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author Zaky
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PaymentHistoryVM {

    @WireVariable
    PaymentHistoryService paymentHistoryService;

    private PaymentHistoryDTO paymentHistoryDTO = new PaymentHistoryDTO();
    private List<PaymentHistoryDTO> paymentHistoryDTOs = new ArrayList<>();

    private ListModelList<StatusPayment> statusPayments;
    private ListModelList<Status> statuses;

    private String idPaymentHistory;
    private StatusPayment statusPayment;
    private Status status;

    private PageNavigation previous;
    private int pageSize = 10;

    Media mediaUploadPayment;
    String mediaNameUploadPayment;
    private String filePathUploadPayment;
    private String pathLocationUploadPayment;

    @Init
    public void init(
            @ExecutionArgParam("previous") PageNavigation previous,
            @ExecutionArgParam("paymentHistoryDTO") PaymentHistoryDTO paymentHistory) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(previous, paymentHistory);
    }

    private void initData() {
        paymentHistoryDTOs = paymentHistoryService.findAll();
        if (paymentHistoryDTOs.isEmpty()) {
            paymentHistoryDTOs = Collections.emptyList();
        }
    }

    private void checkValidity(PageNavigation previous, PaymentHistoryDTO paymentHistory) {
        if (paymentHistory == null) {
            ListModelList<PaymentHistoryDTO> parameterList = new ListModelList<>(paymentHistoryService.findAll());
            String idPaymentHistory = "";
            if (parameterList.isEmpty()) {
                idPaymentHistory = "PH0001";
            } else {
                idPaymentHistory = getLatestObjectID(parameterList, "idPayment");
            }
            paymentHistoryDTO = new PaymentHistoryDTOBuilder()
                    .setIdPayment(idPaymentHistory)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createPaymentHistoryDTO();
        } else {
            this.paymentHistoryDTO = paymentHistory;
            idPaymentHistory = paymentHistoryDTO.getIdPayment();
            this.previous = previous;
        }
    }

    protected String getLatestObjectID(ListModelList list, String attribute) {
        int count = 0;
        int pointer = 0;
        int max = 0;
        String s = "";
        for (Object obj : list) {
            Map<String, Object> map = CommonUtil.convertObject2Map(obj);
            String att = attribute;
            String[] arr = new String[attribute.length()];
            String key = "";

            if (att.contains(".")) {
                arr = att.split("\\.");
                int f = 1;
                for (Object x : arr) {
                    if (f != arr.length) {
                        map = CommonUtil.convertObject2Map(map.get(x.toString()));
                    } else {
                        key = x.toString();
                    }
                    f += 1;
                }
            } else {
                key = att;
            }

            att = map.get(key).toString();

            String temp = "";
            int countTemp = 0;
            for (int i = att.length(); i > 0; i--) {
                if (Character.isLetter(att.charAt(i - 1))) {
                    pointer = i;
                    s = att.substring(0, pointer);
                    break;
                }
                countTemp += 1;
                temp = att.charAt(i - 1) + temp;
            }
            if (Integer.parseInt(temp) > max) {
                max = Integer.parseInt(temp);
            }
            count = countTemp;
        }

        return s + String.format("%0" + count + "d", max + 1);
    }

    @GlobalCommand
    @NotifyChange("paymentHistoryDTOs")
    public void refreshDataPaymentHistory() {
        paymentHistoryDTOs = paymentHistoryService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }

    /* =================== for dashboard functionality =================== */
    @Command("buttonSearchPaymentHistory")
    @NotifyChange("paymentHistoryDTOs")
    public void buttonSearchPaymentHistory(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("idPaymentHistory", idPaymentHistory);
        count = checkCountParameter(count, idPaymentHistory);
        params.put("statusPayment", statusPayment);
        count = checkCountParameter(count, statusPayment);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        paymentHistoryDTOs = paymentHistoryService.findByParams(params);
    }

    @Command("buttonCreatePaymentHistory")
    @NotifyChange("paymentHistoryDTO")
    public void buttonCreatePaymentHistory(@BindingParam("object") PaymentHistoryDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("paymentHistoryDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/paymenthistory_management/create_paymenthistory.zul", window, params);
    }

    @Command("buttonDetailPaymentHistory")
    @NotifyChange("paymentHistory")
    public void buttonDetailPaymentHistory(@BindingParam("object") PaymentHistoryDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("paymentHistoryDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/paymenthistory_management/create_paymenthistory.zul", window, params);
    }

    @Command("buttonDeletePaymentHistory")
    @NotifyChange("paymentHistoryDTOs")
    public void buttonDeletePaymentHistory(@BindingParam("object") PaymentHistoryDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        paymentHistoryDTO = (PaymentHistoryDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Confirmation",
                Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            paymentHistoryService.deleteData(paymentHistoryDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataPaymentHistory", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );
    }

    /* =================== for create functionality =================== */
    @Command("buttonBackCreatePaymentHistory")
    @NotifyChange("paymentHistoryDTO")
    public void buttonBackCreatePaymentHistory(@BindingParam("object") PaymentHistoryDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        window.detach();
    }

    @Command("buttonUploadPayment")
    @NotifyChange({"mediaUploadPayment", "pathLocationUploadPayment"})
    public void buttonUploadPayment(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }

        if (upEvent != null) {
            mediaUploadPayment = upEvent.getMedia();
            mediaUploadPayment = upEvent.getMedia();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            filePathUploadPayment = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            filePathUploadPayment = filePathUploadPayment + "\\" + "files" + "\\" + "brooks2" + "\\" + year + "\\" + month + "\\" + day + "\\";

            File baseDir = new File(filePathUploadPayment);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            Files.copy(new File(filePathUploadPayment + mediaUploadPayment.getName()), mediaUploadPayment.getStreamData());
            setMediaNameUploadPayment(filePathUploadPayment + mediaUploadPayment.getName());
            pathLocationUploadPayment = "/" + "files" + "/" + "brooks2" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadPayment.getName();
        } else {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            mediaNameUploadPayment = "";
            pathLocationUploadPayment = "/" + "files" + "/" + "brooks2"
                    + "t" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadPayment.getName();
            Messagebox.show("File : " + mediaUploadPayment + " Bukan File PDF", "Error", Messagebox.OK, Messagebox.ERROR);

        }
    }

    @Command("buttonSaveDataPaymentHistory")
    @NotifyChange("paymentHistoryDTO")
    public void buttonSaveDataPaymentHistory(@BindingParam("object") PaymentHistoryDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        paymentHistoryService.SaveOrUpdate(paymentHistoryDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataPaymentHistory", null);
        window.detach();
    }

    /* ============= Getter Setter ============= */
    public PaymentHistoryDTO getPaymentHistoryDTO() {
        return paymentHistoryDTO;
    }

    public void setPaymentHistoryDTO(PaymentHistoryDTO paymentHistoryDTO) {
        this.paymentHistoryDTO = paymentHistoryDTO;
    }

    public List<PaymentHistoryDTO> getPaymentHistoryDTOs() {
        return paymentHistoryDTOs;
    }

    public void setPaymentHistoryDTOs(List<PaymentHistoryDTO> paymentHistoryDTOs) {
        this.paymentHistoryDTOs = paymentHistoryDTOs;
    }

    public ListModelList<StatusPayment> getStatusPayments() {
        return new ListModelList<>(StatusPayment.values());
    }

    public void setStatusPayments(ListModelList<StatusPayment> statusPayments) {
        this.statusPayments = statusPayments;
    }

    public ListModelList<Status> getStatuses() {
        return new ListModelList<>(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public String getIdPaymentHistory() {
        return idPaymentHistory;
    }

    public void setIdPaymentHistory(String idPaymentHistory) {
        this.idPaymentHistory = idPaymentHistory;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PageNavigation getPrevious() {
        return previous;
    }

    public void setPrevious(PageNavigation previous) {
        this.previous = previous;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Media getMediaUploadPayment() {
        return mediaUploadPayment;
    }

    public void setMediaUploadPayment(Media mediaUploadPayment) {
        this.mediaUploadPayment = mediaUploadPayment;
    }

    public String getMediaNameUploadPayment() {
        return mediaNameUploadPayment;
    }

    public void setMediaNameUploadPayment(String mediaNameUploadPayment) {
        this.mediaNameUploadPayment = mediaNameUploadPayment;
    }

    public String getFilePathUploadPayment() {
        return filePathUploadPayment;
    }

    public void setFilePathUploadPayment(String filePathUploadPayment) {
        this.filePathUploadPayment = filePathUploadPayment;
    }

    public String getPathLocationUploadPayment() {
        return pathLocationUploadPayment;
    }

    public void setPathLocationUploadPayment(String pathLocationUploadPayment) {
        this.pathLocationUploadPayment = pathLocationUploadPayment;
    }
    

}
