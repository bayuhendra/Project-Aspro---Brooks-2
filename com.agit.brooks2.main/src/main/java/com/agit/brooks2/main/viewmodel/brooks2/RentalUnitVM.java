package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.RentalUnitService;
import com.agit.brooks2.common.dto.core.RentalUnitDTO;
import com.agit.brooks2.common.dto.core.RentalUnitDTOBuilder;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.status.Status;
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
public class RentalUnitVM {
    @WireVariable
    RentalUnitService rentalUnitService;

    private String idRentalUnit;
    private String nameRentalUnit;
    private Status status;

    private ListModelList<Status> statuses;
    private RentalUnitDTO rentalUnitDTO = new RentalUnitDTO();
    private List<RentalUnitDTO> rentalUnitDTOs = new ArrayList<>();

    private PageNavigation previous;
    private int pageSize = 10;

    Media mediaUploadRentalUnit;
    String mediaNameUploadRentalUnit;
    private String filePathUploadRentalUnit;
    private String pathLocationUploadRentalUnit;

    @Init
    public void init(
            @ExecutionArgParam("rentalUnitDTO") RentalUnitDTO rentalUnit,
            @ExecutionArgParam("previous") PageNavigation previous) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(rentalUnit, previous);
    }

    private void initData() {

        rentalUnitDTOs = rentalUnitService.findAll();
        if (rentalUnitDTOs.isEmpty()) {
            rentalUnitDTOs = Collections.emptyList();
        }
    }

    private void checkValidity(RentalUnitDTO rentalUnit, PageNavigation previous) {
        if (rentalUnit == null) {
            ListModelList<RentalUnitDTO> parameterList = new ListModelList<>(rentalUnitService.findAll());
            String idRentalUnit = "";
            if (parameterList.isEmpty()) {
                idRentalUnit = "FUR001";
            } else {
                idRentalUnit = getLatestObjectID(parameterList, "idRentalUnit");
            }
            rentalUnitDTO = new RentalUnitDTOBuilder()
                    .setIdRentalUnit(idRentalUnit)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createRentalUnitDTO();
        } else {
            this.rentalUnitDTO = rentalUnit;
            idRentalUnit = rentalUnitDTO.getIdRentalUnit();
            mediaNameUploadRentalUnit = rentalUnitDTO.getUrlPhotoRentalUnit();
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
    @NotifyChange("rentalUnitDTOs")
    public void refreshDataRentalUnit() {
        rentalUnitDTOs = rentalUnitService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }

    /* =================== for dashboard functionality =================== */
    @Command("buttonSearchRentalUnit")
    @NotifyChange("rentalUnitDTOs")
    public void buttonSearchRentalUnit(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("idRentalUnit", idRentalUnit);
        count = checkCountParameter(count, idRentalUnit);
        params.put("nameRentalUnit", nameRentalUnit);
        count = checkCountParameter(count, nameRentalUnit);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        rentalUnitDTOs = rentalUnitService.findByParams(params);
    }

    @Command("buttonFormCreateRentalUnit")
    @NotifyChange("rentalUnitDTO")
    public void buttonFormCreateRentalUnit(@BindingParam("object") RentalUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("rentalUnitDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/rentalunit_management/create_rentalunit.zul", window, params);
    }

    @Command("buttonDetailRentalUnit")
    @NotifyChange("rentalUnit")
    public void buttonDetailRentalUnit(@BindingParam("object") RentalUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("rentalUnitDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/rentalunit_management/create_rentalunit.zul", window, params);
    }

    @Command("buttonDeleteRentalUnit")
    @NotifyChange("rentalUnitDTOs")
    public void buttonDeleteRentalUnit(@BindingParam("object") RentalUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        rentalUnitDTO = (RentalUnitDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            rentalUnitService.deleteData(rentalUnitDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataRentalUnit", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );

    }

    /* =================== for create functionality =================== */
    @Command("buttonBackCreateRentalUnit")
    @NotifyChange("rentalUnitDTO")
    public void buttonBackCreateRentalUnit(@BindingParam("object") RentalUnitDTO obj, @ContextParam(ContextType.VIEW) Window window
    ) {
        window.detach();
    }

    @Command("buttonUploadRentalUnit")
    @NotifyChange({"mediaUploadRentalUnit", "pathLocationUploadRentalUnit"})
    public void buttonUploadRentalUnit(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }

        if (upEvent != null) {
            mediaUploadRentalUnit = upEvent.getMedia();
            mediaUploadRentalUnit = upEvent.getMedia();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            filePathUploadRentalUnit = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            filePathUploadRentalUnit = filePathUploadRentalUnit + "\\" + "files" + "\\" + "brooks2" + "\\" + year + "\\" + month + "\\" + day + "\\";

            File baseDir = new File(filePathUploadRentalUnit);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            Files.copy(new File(filePathUploadRentalUnit + mediaUploadRentalUnit.getName()), mediaUploadRentalUnit.getStreamData());
            setMediaNameUploadRentalUnit(filePathUploadRentalUnit + mediaUploadRentalUnit.getName());
            pathLocationUploadRentalUnit = "/" + "files" + "/" + "brooks2" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadRentalUnit.getName();
        } else {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            mediaNameUploadRentalUnit = "";
            pathLocationUploadRentalUnit = "/" + "files" + "/" + "brooks2"
                    + "t" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadRentalUnit.getName();
            Messagebox.show("File : " + mediaUploadRentalUnit + " Bukan File PDF", "Error", Messagebox.OK, Messagebox.ERROR);

        }
    }

    @Command("buttonSaveDataRentalUnit")
    @NotifyChange("rentalUnitDTO")
    public void buttonSaveDataRentalUnit(@BindingParam("object") RentalUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        if (pathLocationUploadRentalUnit == null) {
            pathLocationUploadRentalUnit = rentalUnitDTO.getUrlPhotoRentalUnit();
        }
        rentalUnitDTO.setUrlPhotoRentalUnit(pathLocationUploadRentalUnit);
        rentalUnitService.SaveOrUpdate(rentalUnitDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataRentalUnit", null);
        window.detach();
    }
    
    /* ================= Getter Setter ================= */

    public String getIdRentalUnit() {
        return idRentalUnit;
    }

    public void setIdRentalUnit(String idRentalUnit) {
        this.idRentalUnit = idRentalUnit;
    }

    public String getNameRentalUnit() {
        return nameRentalUnit;
    }

    public void setNameRentalUnit(String nameRentalUnit) {
        this.nameRentalUnit = nameRentalUnit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ListModelList<Status> getStatuses() {
        return new ListModelList<>(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public RentalUnitDTO getRentalUnitDTO() {
        return rentalUnitDTO;
    }

    public void setRentalUnitDTO(RentalUnitDTO rentalUnitDTO) {
        this.rentalUnitDTO = rentalUnitDTO;
    }

    public List<RentalUnitDTO> getRentalUnitDTOs() {
        return rentalUnitDTOs;
    }

    public void setRentalUnitDTOs(List<RentalUnitDTO> rentalUnitDTOs) {
        this.rentalUnitDTOs = rentalUnitDTOs;
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

    public Media getMediaUploadRentalUnit() {
        return mediaUploadRentalUnit;
    }

    public void setMediaUploadRentalUnit(Media mediaUploadRentalUnit) {
        this.mediaUploadRentalUnit = mediaUploadRentalUnit;
    }

    public String getMediaNameUploadRentalUnit() {
        return mediaNameUploadRentalUnit;
    }

    public void setMediaNameUploadRentalUnit(String mediaNameUploadRentalUnit) {
        this.mediaNameUploadRentalUnit = mediaNameUploadRentalUnit;
    }

    public String getFilePathUploadRentalUnit() {
        return filePathUploadRentalUnit;
    }

    public void setFilePathUploadRentalUnit(String filePathUploadRentalUnit) {
        this.filePathUploadRentalUnit = filePathUploadRentalUnit;
    }

    public String getPathLocationUploadRentalUnit() {
        return pathLocationUploadRentalUnit;
    }

    public void setPathLocationUploadRentalUnit(String pathLocationUploadRentalUnit) {
        this.pathLocationUploadRentalUnit = pathLocationUploadRentalUnit;
    }
    
}
