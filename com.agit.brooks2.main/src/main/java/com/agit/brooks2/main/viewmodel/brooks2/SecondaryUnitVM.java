package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.HargaPenawaranService;
import com.agit.brooks2.common.application.SecondaryUnitService;
import com.agit.brooks2.common.dto.core.SecondaryUnitDTO;
import com.agit.brooks2.common.dto.core.SecondaryUnitDTOBuilder;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTO;
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
public class SecondaryUnitVM {

    @WireVariable
    SecondaryUnitService secondaryUnitService;

    @WireVariable
    HargaPenawaranService hargaPenawaranService;

    private String idSecondaryUnit;
    private String nameSecondaryUnit;
    private Status status;
    private String hargaSelected;

    private ListModelList<Status> statuses;
    private SecondaryUnitDTO secondaryUnitDTO = new SecondaryUnitDTO();
    private List<SecondaryUnitDTO> secondaryUnitDTOs = new ArrayList<>();
    private List<HargaPenawaranDTO> hargaPenawaranDTOs = new ArrayList<>();

    private List<String> listHarga = new ArrayList<>();

    private PageNavigation previous;
    private int pageSize = 10;

    Media mediaUploadSecondaryUnit;
    String mediaNameUploadSecondaryUnit;
    private String filePathUploadSecondaryUnit;
    private String pathLocationUploadSecondaryUnit;

    @Init
    public void init(
            @ExecutionArgParam("secondaryUnitDTO") SecondaryUnitDTO secondaryUnit,
            @ExecutionArgParam("previous") PageNavigation previous) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(secondaryUnit, previous);
    }

    private void initData() {

        secondaryUnitDTOs = secondaryUnitService.findAll();
        if (secondaryUnitDTOs.isEmpty()) {
            secondaryUnitDTOs = Collections.emptyList();
        }
        hargaPenawaranDTOs = hargaPenawaranService.findAll();
        for (HargaPenawaranDTO h : hargaPenawaranDTOs) {
            listHarga.add(h.getHarga());
        }
    }

    private void checkValidity(SecondaryUnitDTO secondaryUnit, PageNavigation previous) {
        if (secondaryUnit == null) {
            ListModelList<SecondaryUnitDTO> parameterList = new ListModelList<>(secondaryUnitService.findAll());
            String idSecondaryUnit = "";
            if (parameterList.isEmpty()) {
                idSecondaryUnit = "FUR001";
            } else {
                idSecondaryUnit = getLatestObjectID(parameterList, "idSecondaryUnit");
            }
            secondaryUnitDTO = new SecondaryUnitDTOBuilder()
                    .setIdSecondaryUnit(idSecondaryUnit)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createSecondaryUnitDTO();
        } else {
            this.secondaryUnitDTO = secondaryUnit;
            idSecondaryUnit = secondaryUnitDTO.getIdSecondaryUnit();
            mediaNameUploadSecondaryUnit = secondaryUnitDTO.getUrlPhotoSecondaryUnit();
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
    @NotifyChange("secondaryUnitDTOs")
    public void refreshDataSecondaryUnit() {
        secondaryUnitDTOs = secondaryUnitService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }

    /* =================== for dashboard functionality =================== */
    @Command("buttonSearchSecondaryUnit")
    @NotifyChange("secondaryUnitDTOs")
    public void buttonSearchSecondaryUnit(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("idSecondaryUnit", idSecondaryUnit);
        count = checkCountParameter(count, idSecondaryUnit);
        params.put("nameSecondaryUnit", nameSecondaryUnit);
        count = checkCountParameter(count, nameSecondaryUnit);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        secondaryUnitDTOs = secondaryUnitService.findByParams(params);
    }

    @Command("buttonFormCreateSecondaryUnit")
    @NotifyChange("secondaryUnitDTO")
    public void buttonFormCreateSecondaryUnit(@BindingParam("object") SecondaryUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("secondaryUnitDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/secondaryunit_management/create_secondaryunit.zul", window, params);
    }

    @Command("buttonDetailSecondaryUnit")
    @NotifyChange("secondaryUnit")
    public void buttonDetailSecondaryUnit(@BindingParam("object") SecondaryUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("secondaryUnitDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/secondaryunit_management/create_secondaryunit.zul", window, params);
    }

    @Command("buttonDeleteSecondaryUnit")
    @NotifyChange("secondaryUnitDTOs")
    public void buttonDeleteSecondaryUnit(@BindingParam("object") SecondaryUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        secondaryUnitDTO = (SecondaryUnitDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            secondaryUnitService.deleteData(secondaryUnitDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataSecondaryUnit", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );

    }

    /* =================== for create functionality =================== */
    @Command("buttonBackCreateSecondaryUnit")
    @NotifyChange("secondaryUnitDTO")
    public void buttonBackCreateSecondaryUnit(@BindingParam("object") SecondaryUnitDTO obj, @ContextParam(ContextType.VIEW) Window window
    ) {
        window.detach();
    }

    @Command("buttonUploadSecondaryUnit")
    @NotifyChange({"mediaNameUploadSecondaryUnit", "pathLocationUploadSecondaryUnit"})
    public void buttonUploadSecondaryUnit(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }

        if (upEvent != null) {
            mediaUploadSecondaryUnit = upEvent.getMedia();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            filePathUploadSecondaryUnit = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            filePathUploadSecondaryUnit = filePathUploadSecondaryUnit + "\\" + "files" + "\\" + "brooks2" + "\\" + year + "\\" + month + "\\" + day + "\\";

            File baseDir = new File(filePathUploadSecondaryUnit);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            Files.copy(new File(filePathUploadSecondaryUnit + mediaUploadSecondaryUnit.getName()), mediaUploadSecondaryUnit.getStreamData());
            setMediaNameUploadSecondaryUnit(filePathUploadSecondaryUnit + mediaUploadSecondaryUnit.getName());
            pathLocationUploadSecondaryUnit = "/" + "files" + "/" + "brooks2" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadSecondaryUnit.getName();
        } else {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            mediaNameUploadSecondaryUnit = "";
            pathLocationUploadSecondaryUnit = "/" + "files" + "/" + "brooks2"
                    + "t" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadSecondaryUnit.getName();
            Messagebox.show("File : " + mediaUploadSecondaryUnit + " Bukan File PDF", "Error", Messagebox.OK, Messagebox.ERROR);

        }
    }

    @Command("buttonSaveDataSecondaryUnit")
    @NotifyChange("secondaryUnitDTO")
    public void buttonSaveDataSecondaryUnit(@BindingParam("object") SecondaryUnitDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        
        if (pathLocationUploadSecondaryUnit == null) {
            pathLocationUploadSecondaryUnit = secondaryUnitDTO.getUrlPhotoSecondaryUnit();
        }
        secondaryUnitDTO.setUrlPhotoSecondaryUnit(pathLocationUploadSecondaryUnit);
        secondaryUnitService.SaveOrUpdate(secondaryUnitDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataSecondaryUnit", null);
        window.detach();
    }

    /* ================= Getter Setter ================= */
    public String getIdSecondaryUnit() {
        return idSecondaryUnit;
    }

    public void setIdSecondaryUnit(String idSecondaryUnit) {
        this.idSecondaryUnit = idSecondaryUnit;
    }

    public String getNameSecondaryUnit() {
        return nameSecondaryUnit;
    }

    public void setNameSecondaryUnit(String nameSecondaryUnit) {
        this.nameSecondaryUnit = nameSecondaryUnit;
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

    public SecondaryUnitDTO getSecondaryUnitDTO() {
        return secondaryUnitDTO;
    }

    public void setSecondaryUnitDTO(SecondaryUnitDTO secondaryUnitDTO) {
        this.secondaryUnitDTO = secondaryUnitDTO;
    }

    public List<SecondaryUnitDTO> getSecondaryUnitDTOs() {
        return secondaryUnitDTOs;
    }

    public void setSecondaryUnitDTOs(List<SecondaryUnitDTO> secondaryUnitDTOs) {
        this.secondaryUnitDTOs = secondaryUnitDTOs;
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

    public Media getMediaUploadSecondaryUnit() {
        return mediaUploadSecondaryUnit;
    }

    public void setMediaUploadSecondaryUnit(Media mediaUploadSecondaryUnit) {
        this.mediaUploadSecondaryUnit = mediaUploadSecondaryUnit;
    }

    public String getMediaNameUploadSecondaryUnit() {
        return mediaNameUploadSecondaryUnit;
    }

    public void setMediaNameUploadSecondaryUnit(String mediaNameUploadSecondaryUnit) {
        this.mediaNameUploadSecondaryUnit = mediaNameUploadSecondaryUnit;
    }

    public String getFilePathUploadSecondaryUnit() {
        return filePathUploadSecondaryUnit;
    }

    public void setFilePathUploadSecondaryUnit(String filePathUploadSecondaryUnit) {
        this.filePathUploadSecondaryUnit = filePathUploadSecondaryUnit;
    }

    public String getPathLocationUploadSecondaryUnit() {
        return pathLocationUploadSecondaryUnit;
    }

    public void setPathLocationUploadSecondaryUnit(String pathLocationUploadSecondaryUnit) {
        this.pathLocationUploadSecondaryUnit = pathLocationUploadSecondaryUnit;
    }

    public List<HargaPenawaranDTO> getHargaPenawaranDTOs() {
        return hargaPenawaranDTOs;
    }

    public void setHargaPenawaranDTOs(List<HargaPenawaranDTO> hargaPenawaranDTOs) {
        this.hargaPenawaranDTOs = hargaPenawaranDTOs;
    }

    public List<String> getListHarga() {
        return listHarga;
    }

    public void setListHarga(List<String> listHarga) {
        this.listHarga = listHarga;
    }

    public String getHargaSelected() {
        return hargaSelected;
    }

    public void setHargaSelected(String hargaSelected) {
        this.hargaSelected = hargaSelected;
    }

}
