package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.HargaPenawaranService;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTO;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTOBuilder;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.zul.CommonViewModel;
import static com.agit.brooks2.shared.zul.CommonViewModel.showInformationMessagebox;
import com.agit.brooks2.shared.zul.PageNavigation;
import com.agit.brooks2.util.CommonUtil;
import com.agit.brooks2.util.JDCStringUtil;
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
 * @author 3AD
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EstimatedPriceVM {

    @WireVariable
    HargaPenawaranService hargaPenawaranService;

    private HargaPenawaranDTO hargaPenawaranDTO = new HargaPenawaranDTO();
    private List<HargaPenawaranDTO> hargaPenawaranDTOs = new ArrayList<>();
    private ListModelList<Status> statuses = new ListModelList<>();

    private String hargaPenawaranID;
    private String harga;
    private Status status;

    private PageNavigation previous;
    private int pageSize = 10;

    @Init
    public void init(
            @ExecutionArgParam("hargaPenawaranDTO") HargaPenawaranDTO hargaPenawaran,
            @ExecutionArgParam("previous") PageNavigation previous) {

        initData();
        checkValidity(hargaPenawaran, previous);

    }

    private void initData() {
        hargaPenawaranDTOs = hargaPenawaranService.findAll();
        if (hargaPenawaranDTOs.isEmpty()) {
            hargaPenawaranDTOs = Collections.emptyList();
        }
    }

    private void checkValidity(HargaPenawaranDTO hargaPenawaran, PageNavigation previous) {
        if (hargaPenawaran == null) {
            ListModelList<HargaPenawaranDTO> parameterList = new ListModelList<>(hargaPenawaranService.findAll());
            String hargaPenawaranID = "";
            if (parameterList.isEmpty()) {
                hargaPenawaranID = "H001";
            } else {
                hargaPenawaranID = getLatestObjectID(parameterList, "hargaPenawaranID");
            }
            hargaPenawaranDTO = new HargaPenawaranDTOBuilder()
                    .setHargaPenawaranID(hargaPenawaranID)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createHargaPenawaranDTO();
        } else {
            this.hargaPenawaranDTO = hargaPenawaran;
            hargaPenawaranID = hargaPenawaranDTO.getHargaPenawaranID();
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
    @NotifyChange("hargaPenawaranDTOs")
    public void refreshHargaPenawaran() {
        hargaPenawaranDTOs = hargaPenawaranService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (JDCStringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }
    /*-------------------------------------------- Functionality ---------------------------------------------------*/

    @Command("buttonSearchHargaPenawaran")
    @NotifyChange("hargaPenawaranDTOs")
    public void buttonSearchHargaPenawaran(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("hargaPenawaranID", hargaPenawaranID);
        count = checkCountParameter(count, hargaPenawaranID);
        params.put("harga", harga);
        count = checkCountParameter(count, harga);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        hargaPenawaranDTOs = hargaPenawaranService.findByParams(params);
    }

    @Command("buttonFormCreateHargaPenawaran")
    @NotifyChange("hargaPenawaranDTO")
    public void buttonFormCreateHargaPenawaran(@BindingParam("object") HargaPenawaranDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("hargaPenawaranDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/setup_danapenghargaan/add_danaPenghargaan.zul", window, params);
    }

    @Command("buttonDelete")
    @NotifyChange("hargaPenawaranDTOs")
    public void buttonDelete(@BindingParam("object") HargaPenawaranDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        hargaPenawaranDTO = (HargaPenawaranDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            hargaPenawaranService.delete(hargaPenawaranDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshHargaPenawaran", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );

    }

    /* =================== for create functionality =================== */
    @Command("buttonBack")
    @NotifyChange("hargaPenawaranDTO")
    public void buttonBack(@BindingParam("object") HargaPenawaranDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        window.detach();
    }

    @Command("buttonSaveData")
    @NotifyChange("hargaPenawaranDTO")
    public void buttonSaveData(@BindingParam("object") HargaPenawaranDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        hargaPenawaranService.saveOrUpdate(hargaPenawaranDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshHargaPenawaran", null);
        window.detach();
    }

    public HargaPenawaranDTO getHargaPenawaranDTO() {
        return hargaPenawaranDTO;
    }

    public void setHargaPenawaranDTO(HargaPenawaranDTO hargaPenawaranDTO) {
        this.hargaPenawaranDTO = hargaPenawaranDTO;
    }

    public List<HargaPenawaranDTO> getHargaPenawaranDTOs() {
        return hargaPenawaranDTOs;
    }

    public void setHargaPenawaranDTOs(List<HargaPenawaranDTO> hargaPenawaranDTOs) {
        this.hargaPenawaranDTOs = hargaPenawaranDTOs;
    }

    public ListModelList<Status> getStatuses() {
        return new ListModelList(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public String getHargaPenawaranID() {
        return hargaPenawaranID;
    }

    public void setHargaPenawaranID(String hargaPenawaranID) {
        this.hargaPenawaranID = hargaPenawaranID;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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
