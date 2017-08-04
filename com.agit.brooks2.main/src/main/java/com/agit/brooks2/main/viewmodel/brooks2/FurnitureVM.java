package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.FurnitureService;
import com.agit.brooks2.common.dto.masterdata.FurnitureDTO;
import com.agit.brooks2.common.dto.masterdata.FurnitureDTOBuilder;
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
public class FurnitureVM {

    @WireVariable
    FurnitureService furnitureService;

    private String idFurniture;
    private String nameFurniture;
    private Status status;

    private ListModelList<Status> statuses;
    private FurnitureDTO furnitureDTO = new FurnitureDTO();
    private List<FurnitureDTO> furnitureDTOs = new ArrayList<>();

    private PageNavigation previous;
    private int pageSize = 10;

    Media mediaUploadFurniture;
    String mediaNameUploadFurniture;
    private String filePathUploadFurniture;
    private String pathLocationUploadFurniture;

    @Init
    public void init(
            @ExecutionArgParam("furnitureDTO") FurnitureDTO furniture,
            @ExecutionArgParam("previous") PageNavigation previous) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(furniture, previous);
    }

    private void initData() {

        furnitureDTOs = furnitureService.findAll();
        if (furnitureDTOs.isEmpty()) {
            furnitureDTOs = Collections.emptyList();
        }
    }

    private void checkValidity(FurnitureDTO furniture, PageNavigation previous) {
        if (furniture == null) {
            ListModelList<FurnitureDTO> parameterList = new ListModelList<>(furnitureService.findAll());
            String idFurniture = "";
            if (parameterList.isEmpty()) {
                idFurniture = "FUR001";
            } else {
                idFurniture = getLatestObjectID(parameterList, "idFurniture");
            }
            furnitureDTO = new FurnitureDTOBuilder()
                    .setIdFurniture(idFurniture)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createFurnitureDTO();
        } else {
            this.furnitureDTO = furniture;
            idFurniture = furnitureDTO.getIdFurniture();
            mediaNameUploadFurniture = furnitureDTO.getUrlPhoto();
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
    @NotifyChange("furnitureDTOs")
    public void refreshDataFurniture() {
        furnitureDTOs = furnitureService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }

    /* =================== for dashboard functionality =================== */
    @Command("buttonSearchFurniture")
    @NotifyChange("furnitureDTOs")
    public void buttonSearchFurniture(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("idFurniture", idFurniture);
        count = checkCountParameter(count, idFurniture);
        params.put("nameFurniture", nameFurniture);
        count = checkCountParameter(count, nameFurniture);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        furnitureDTOs = furnitureService.findByParams(params);
    }

    @Command("buttonFormCreateFurniture")
    @NotifyChange("furnitureDTO")
    public void buttonFormCreateFurniture(@BindingParam("object") FurnitureDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("furnitureDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/furniture_management/create_furniture.zul", window, params);
    }

    @Command("buttonDetailFurniture")
    @NotifyChange("furniture")
    public void buttonDetailFurniture(@BindingParam("object") FurnitureDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("furnitureDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/furniture_management/create_furniture.zul", window, params);
    }

    @Command("buttonDeleteFurniture")
    @NotifyChange("furnitureDTOs")
    public void buttonDeleteFurniture(@BindingParam("object") FurnitureDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        furnitureDTO = (FurnitureDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            furnitureService.deleteData(furnitureDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataFurniture", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );

    }

    /* =================== for create functionality =================== */
    @Command("buttonBackCreateFurniture")
    @NotifyChange("furnitureDTO")
    public void buttonBackCreateFurniture(@BindingParam("object") FurnitureDTO obj, @ContextParam(ContextType.VIEW) Window window
    ) {
        window.detach();
    }

    @Command("buttonUploadPicFurniture")
    @NotifyChange({"mediaUploadFurniture", "pathLocationUploadFurniture"})
    public void buttonUploadPicFurniture(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }

        if (upEvent != null) {
            mediaUploadFurniture = upEvent.getMedia();
            mediaUploadFurniture = upEvent.getMedia();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            filePathUploadFurniture = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            filePathUploadFurniture = filePathUploadFurniture + "\\" + "files" + "\\" + "brooks2" + "\\" + year + "\\" + month + "\\" + day + "\\";

            File baseDir = new File(filePathUploadFurniture);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            Files.copy(new File(filePathUploadFurniture + mediaUploadFurniture.getName()), mediaUploadFurniture.getStreamData());
            setMediaNameUploadFurniture(mediaNameUploadFurniture);
            setMediaNameUploadFurniture(filePathUploadFurniture + mediaUploadFurniture.getName());
            filePathUploadFurniture = "/" + "files" + "/" + "brooks2" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadFurniture.getName();
        } else {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            mediaNameUploadFurniture = "";
            pathLocationUploadFurniture = "/" + "files" + "/" + "brooks2"
                    + "t" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadFurniture.getName();
            Messagebox.show("File : " + mediaUploadFurniture + " Bukan File PDF", "Error", Messagebox.OK, Messagebox.ERROR);

        }
    }

    @Command("buttonSaveDataFurniture")
    @NotifyChange("furnitureDTO")
    public void buttonSaveDataFurniture(@BindingParam("object") FurnitureDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        if (pathLocationUploadFurniture == null) {
            pathLocationUploadFurniture = furnitureDTO.getUrlPhoto();
        }
        furnitureDTO.setUrlPhoto(pathLocationUploadFurniture);
        furnitureService.SaveOrUpdate(furnitureDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataFurniture", null);
        window.detach();
    }

    @Command("buttonPopUp")
    @NotifyChange("furnitureDTO")
    public void buttonPopUp(@ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        CommonViewModel.navigateToWithoutDetach("/brooks2/customer/furniture/dashboard_furniture.zul", window, params);
    }
    /* ================= Getter Setter ================= */

    public FurnitureDTO getFurnitureDTO() {
        return furnitureDTO;
    }

    public void setFurnitureDTO(FurnitureDTO furnitureDTO) {
        this.furnitureDTO = furnitureDTO;
    }

    public List<FurnitureDTO> getFurnitureDTOs() {
        return furnitureDTOs;
    }

    public void setFurnitureDTOs(List<FurnitureDTO> furnitureDTOs) {
        this.furnitureDTOs = furnitureDTOs;
    }

    public String getIdFurniture() {
        return idFurniture;
    }

    public void setIdFurniture(String idFurniture) {
        this.idFurniture = idFurniture;
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

    public Media getMediaUploadFurniture() {
        return mediaUploadFurniture;
    }

    public void setMediaUploadFurniture(Media mediaUploadFurniture) {
        this.mediaUploadFurniture = mediaUploadFurniture;
    }

    public String getMediaNameUploadFurniture() {
        return mediaNameUploadFurniture;
    }

    public void setMediaNameUploadFurniture(String mediaNameUploadFurniture) {
        this.mediaNameUploadFurniture = mediaNameUploadFurniture;
    }

    public String getFilePathUploadFurniture() {
        return filePathUploadFurniture;
    }

    public void setFilePathUploadFurniture(String filePathUploadFurniture) {
        this.filePathUploadFurniture = filePathUploadFurniture;
    }

    public String getPathLocationUploadFurniture() {
        return pathLocationUploadFurniture;
    }

    public void setPathLocationUploadFurniture(String pathLocationUploadFurniture) {
        this.pathLocationUploadFurniture = pathLocationUploadFurniture;
    }

    public String getNameFurniture() {
        return nameFurniture;
    }

    public void setNameFurniture(String nameFurniture) {
        this.nameFurniture = nameFurniture;
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

}
