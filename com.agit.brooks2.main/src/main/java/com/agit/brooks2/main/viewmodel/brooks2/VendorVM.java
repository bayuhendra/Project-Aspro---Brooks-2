package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.VendorService;
import com.agit.brooks2.common.dto.masterdata.VendorDTO;
import com.agit.brooks2.common.dto.masterdata.VendorDTOBuilder;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.zul.CommonViewModel;
import static com.agit.brooks2.shared.zul.CommonViewModel.showInformationMessagebox;
import com.agit.brooks2.shared.zul.PageNavigation;
import com.agit.brooks2.util.CommonUtil;
import com.agit.brooks2.util.StringUtil;
import java.util.ArrayList;
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
public class VendorVM {

    @WireVariable
    VendorService vendorService;

    private VendorDTO vendorDTO = new VendorDTO();
    private List<VendorDTO> vendorDTOs = new ArrayList<>();

    private ListModelList<Status> statuses = new ListModelList<>();

    private String vendorID;
    private String namaVendor;
    private Status statusVendor;

    private PageNavigation previous;
    private boolean checked;
    private int pageSize = 15;
    private int activePage = 0;
    private int selectedIndex;
    private int totalSize = 0;

    @Init
    void init(
            @ExecutionArgParam("vendorDTO") VendorDTO vendor,
            @ExecutionArgParam("previous") PageNavigation previous) {

        initData();
        checkValidity(vendor, previous);

    }

    private void initData() {
        vendorDTOs = vendorService.findAll();
    }

    private void checkValidity(VendorDTO vendor, PageNavigation previous) {
        if (vendor == null) {
            ListModelList<VendorDTO> parameterList = new ListModelList<>(vendorService.findAll());
            String vendorID = "";
            if (parameterList.isEmpty()) {
                vendorID = "VEN001";
            } else {
                vendorID = getLatestObjectID(parameterList, "vendorID");
            }
            vendorDTO = new VendorDTOBuilder()
                    .setVendorID(vendorID)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createVendorDTO();
        } else {
            this.vendorDTO = vendor;
            vendorID = vendorDTO.getVendorID();
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

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }
    /*------------------------------------------------------ Fungsionality ----------------------------------------------------------------*/

    @Command("buttonSearchVendor")
    @NotifyChange("vendorDTOs")
    public void buttonSearchVendor(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("vendorID", vendorID);
        count = checkCountParameter(count, vendorID);
        params.put("namaVendor", namaVendor);
        count = checkCountParameter(count, namaVendor);
        params.put("statusVendor", statusVendor);
        count = checkCountParameter(count, statusVendor);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        vendorDTOs = vendorService.findByParams(params);
    }

    @Command("buttonFormCreateVendor")
    @NotifyChange("vendorDTO")
    public void buttonFormCreateVendor(@BindingParam("object") VendorDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("vendorDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/setup_vendor/setup_vendor.zul", window, params);
    }

    @Command("buttonClose")
    @NotifyChange("vendorDTOs")
    public void buttonClose(@BindingParam("object") VendorDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        window.detach();
    }

    @Command("buttonSaveDataVendor")
    @NotifyChange("vendorDTO")
    public void buttonSaveDataVendor(@BindingParam("object") VendorDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        vendorService.saveOrUpdate(vendorDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataVendors", null);
        window.detach();
    }

    @Command("buttonDetailVendor")
    @NotifyChange("vendor")
    public void buttonDetailVendor(@BindingParam("object") VendorDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("vendorDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/setup_vendor/setup_vendor.zul", window, params);
    }

    @Command("buttonDeleteVendor")
    @NotifyChange("vendorDTOs")
    public void buttonDeleteVendor(@BindingParam("object") VendorDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        vendorDTO = (VendorDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Confirmation",
                Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            vendorService.delete(vendorDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataVendors", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );
    }

    @GlobalCommand
    @NotifyChange("vendorDTOs")
    public void refreshDataVendors() {
        vendorDTOs = vendorService.findAll();
    }

    public VendorDTO getVendorDTO() {
        return vendorDTO;
    }

    public void setVendorDTO(VendorDTO vendorDTO) {
        this.vendorDTO = vendorDTO;
    }

    public List<VendorDTO> getVendorDTOs() {
        return vendorDTOs;
    }

    public void setVendorDTOs(List<VendorDTO> vendorDTOs) {
        this.vendorDTOs = vendorDTOs;
    }

    public ListModelList<Status> getStatuses() {
        return new ListModelList<>(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }

    public Status getStatusVendor() {
        return statusVendor;
    }

    public void setStatusVendor(Status statusVendor) {
        this.statusVendor = statusVendor;
    }

    public PageNavigation getPrevious() {
        return previous;
    }

    public void setPrevious(PageNavigation previous) {
        this.previous = previous;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getActivePage() {
        return activePage;
    }

    public void setActivePage(int activePage) {
        this.activePage = activePage;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

}
