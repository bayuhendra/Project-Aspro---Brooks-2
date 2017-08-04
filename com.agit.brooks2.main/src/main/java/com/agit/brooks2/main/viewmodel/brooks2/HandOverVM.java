package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.HandOverService;
import com.agit.brooks2.common.dto.core.HandOverDTO;
import com.agit.brooks2.common.dto.core.HandOverDTOBuilder;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.zul.CommonViewModel;
import static com.agit.brooks2.shared.zul.CommonViewModel.showInformationMessagebox;
import com.agit.brooks2.shared.zul.PageNavigation;
import com.agit.brooks2.util.CommonUtil;
import com.agit.brooks2.util.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
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
public class HandOverVM {

    @WireVariable
    HandOverService handOverService;

    private HandOverDTO handOverDTO = new HandOverDTO();
    private List<HandOverDTO> handOverDTOs = new ArrayList<>();

    private ListModelList<Status> statuses;

    private String idHandOver;
    private Date startDate;
    private Date endDate;
    private Status status;

    private PageNavigation previous;
    private int pageSize = 10;

    @Init
    public void init(
            @ExecutionArgParam("previous") PageNavigation previous,
            @ExecutionArgParam("handOverDTO") HandOverDTO handOver) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(previous, handOver);
    }

    private void initData() {

        handOverDTOs = handOverService.findAll();
        if (handOverDTOs.isEmpty()) {
            handOverDTOs = Collections.emptyList();
        }
    }

    private void checkValidity(PageNavigation previous, HandOverDTO handOver) {
        if (handOver == null) {
            ListModelList<HandOverDTO> parameterList = new ListModelList<>(handOverService.findAll());
            String idHandOver = "";
            if (parameterList.isEmpty()) {
                idHandOver = "Pro0001";
            } else {
                idHandOver = getLatestObjectID(parameterList, "idHandOver");
            }
            handOverDTO = new HandOverDTOBuilder()
                    .setIdHandOver(idHandOver)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createHandOverDTO();
        } else {
            this.handOverDTO = handOver;
            idHandOver = handOverDTO.getIdHandOver();
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
    @NotifyChange("handOverDTOs")
    public void refreshDataHandOver() {
        handOverDTOs = handOverService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }
    
    
/* =================== for dashboard functionality =================== */
    @Command("buttonSearchHandOver")
    @NotifyChange("handOverDTOs")
    public void buttonSearchHandOver(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("idHandOver", idHandOver);
        count = checkCountParameter(count, idHandOver);
        params.put("startDate", startDate);
        count = checkCountParameter(count, startDate);
        params.put("endDate", endDate);
        count = checkCountParameter(count, endDate);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        handOverDTOs = handOverService.findByParams(params);
    }

    @Command("buttonCreateHandOver")
    @NotifyChange("handOverDTO")
    public void buttonCreateHandOver(@BindingParam("object") HandOverDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("handOverDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/handover_management/create_handover.zul", window, params);
    }

    @Command("buttonDetailHandOver")
    @NotifyChange("handOver")
    public void buttonDetailHandOver(@BindingParam("object") HandOverDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("handOverDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/handover_management/create_handover.zul", window, params);
    }

    @Command("buttonDeleteHandOver")
    @NotifyChange("handOverDTOs")
    public void buttonDeleteHandOver(@BindingParam("object") HandOverDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        handOverDTO = (HandOverDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Confirmation",
                Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            handOverService.deleteData(handOverDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataHandOver", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );
    }

    /* =================== for create functionality =================== */
    @Command("buttonBackCreateHandOver")
    @NotifyChange("handOverDTO")
    public void buttonBackCreateHandOver(@BindingParam("object") HandOverDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        window.detach();
    }

    @Command("buttonSaveDataHandOver")
    @NotifyChange("handOverDTO")
    public void buttonSaveDataHandOver(@BindingParam("object") HandOverDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        handOverService.SaveOrUpdate(handOverDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataHandOver", null);
        window.detach();
    }
    
    
    /* =================== getter setter =================== */

    public HandOverDTO getHandOverDTO() {
        return handOverDTO;
    }

    public void setHandOverDTO(HandOverDTO handOverDTO) {
        this.handOverDTO = handOverDTO;
    }

    public List<HandOverDTO> getHandOverDTOs() {
        return handOverDTOs;
    }

    public void setHandOverDTOs(List<HandOverDTO> handOverDTOs) {
        this.handOverDTOs = handOverDTOs;
    }

    public ListModelList<Status> getStatuses() {
        return new ListModelList<>(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public String getIdHandOver() {
        return idHandOver;
    }

    public void setIdHandOver(String idHandOver) {
        this.idHandOver = idHandOver;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
    
}
