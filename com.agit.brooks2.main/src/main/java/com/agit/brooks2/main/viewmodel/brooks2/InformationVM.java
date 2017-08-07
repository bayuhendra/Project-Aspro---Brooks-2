package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.InformationsService;
import com.agit.brooks2.common.application.ProjectService;
import com.agit.brooks2.common.dto.masterdata.InformationsDTO;
import com.agit.brooks2.common.dto.masterdata.InformationsDTOBuilder;
import com.agit.brooks2.common.dto.masterdata.ProjectDTO;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.zul.CommonViewModel;
import static com.agit.brooks2.shared.zul.CommonViewModel.showInformationMessagebox;
import com.agit.brooks2.shared.zul.PageNavigation;
import com.agit.brooks2.util.CommonUtil;
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
 * @author Bayu Hendra Setiawan
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class InformationVM {

    @WireVariable
    InformationsService informationsService;

    @WireVariable
    ProjectService projectService;

    private String idNews;

    private InformationsDTO informationsDTO = new InformationsDTO();
    private List<InformationsDTO> informationsDTOs = new ArrayList<>();

    private ListModelList<Status> statuses;
    private Status status;

    private List<ProjectDTO> projects = new ArrayList<ProjectDTO>();

    private PageNavigation previous;
    private boolean checked;
    private int pageSize = 15;
    private int activePage = 0;
    private int selectedIndex;
    private int totalSize = 0;

    Media mediaUploadPicInformations;
    String mediaNameUploadPicInformations;
    private String filepathUploadPicInformations;
    private String pathLocationUploadPicInformations;

    @Init
    public void init(
            @ExecutionArgParam("informationsDTO") InformationsDTO informations,
            @ExecutionArgParam("previous") PageNavigation previous) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(informations, previous);
    }

    private void initData() {

        informationsDTOs = informationsService.findAll();
        if (informationsDTOs.isEmpty()) {
            informationsDTOs = Collections.emptyList();
        }

        projects = projectService.findAll();
        if (projects.isEmpty()) {
            projects = Collections.emptyList();
        }

    }

    private void checkValidity(InformationsDTO informations, PageNavigation previous) {
        if (informations == null) {
            ListModelList<InformationsDTO> parameterList = new ListModelList<>(informationsService.findAll());
            String idNews = "";
            if (parameterList.isEmpty()) {
                idNews = "NEWS001";
            } else {
                idNews = getLatestObjectID(parameterList, "idNews");
            }
            informationsDTO = new InformationsDTOBuilder()
                    .setIdNews(idNews)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createInformationsDTO();
        } else {
            this.informationsDTO = informations;
            idNews = informationsDTO.getIdNews();
            mediaNameUploadPicInformations = informationsDTO.getUrlPhotoInformation();
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

    @Command("buttonUploadPicInformations")
    @NotifyChange({"mediaNameUploadPicInformations", "pathLocationUploadPicInformations"})
    public void buttonUploadPicInformations(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
        UploadEvent upEvent = null;
        Object objUploadEvent = ctx.getTriggerEvent();

        if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
            upEvent = (UploadEvent) objUploadEvent;
        }

        if (upEvent != null) {
            mediaUploadPicInformations = upEvent.getMedia();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            filepathUploadPicInformations = Executions.getCurrent().getDesktop().getWebApp().getRealPath("/");
            filepathUploadPicInformations = filepathUploadPicInformations + "\\" + "files" + "\\" + "brooks2" + "\\" + year + "\\" + month + "\\" + day + "\\";

            File baseDir = new File(filepathUploadPicInformations);
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

            Files.copy(new File(filepathUploadPicInformations + mediaUploadPicInformations.getName()), mediaUploadPicInformations.getStreamData());
            setMediaNameUploadPicInformations(filepathUploadPicInformations + mediaUploadPicInformations.getName());
            pathLocationUploadPicInformations = "/" + "files" + "/" + "brooks2" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadPicInformations.getName();
        } else {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            int day = now.get(Calendar.DAY_OF_MONTH);
            mediaNameUploadPicInformations = "";
            pathLocationUploadPicInformations = "/" + "files" + "/" + "brooks2"
                    + "t" + "/" + year + "/" + month + "/" + day + "/" + mediaUploadPicInformations.getName();
            Messagebox.show("File : " + mediaUploadPicInformations + " Bukan File PDF", "Error", Messagebox.OK, Messagebox.ERROR);

        }
    }

    @Command("buttonCreateInformation")
    @NotifyChange("informationsDTO")
    public void buttonCreateInformation(@BindingParam("object") InformationsDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("informationsDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/Information  Management/create_information.zul", window, params);
    }

    @Command("buttonBackCreateInformation")
    @NotifyChange("informationsDTO")
    public void buttonBackCreateInformation(@BindingParam("object") InformationsDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        window.detach();
    }

    @Command("buttonSaveDataInformations")
    @NotifyChange("informationsDTO")
    public void buttonSaveDataInformations(@BindingParam("object") InformationsDTO obj, @ContextParam(ContextType.VIEW) Window window) {

        if (pathLocationUploadPicInformations == null) {
            pathLocationUploadPicInformations = informationsDTO.getUrlPhotoInformation();
        }
        informationsDTO.setUrlPhotoInformation(pathLocationUploadPicInformations);
        informationsService.SaveOrUpdate(informationsDTO);
        showInformationMessagebox("Data Informations Successfully");
        BindUtils.postGlobalCommand(null, null, "refreshDataInformations", null);
        window.detach();
    }

    @Command("buttonDetailInformations")
    @NotifyChange("buttonDetailInformations")
    public void buttonDetailInformations(@BindingParam("object") InformationsDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("informationsDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/Information  Management/create_information.zul", window, params);
    }

    @Command("buttonDetailInformationsCus")
    @NotifyChange("buttonDetailInformations")
    public void buttonDetailInformationsCus(@BindingParam("object") InformationsDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("informationsDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/customer/information/detail_information.zul", window, params);
    }

    @GlobalCommand
    @NotifyChange("informationsDTOs")
    public void refreshDataInformations() {
        informationsDTOs = informationsService.findAll();
    }

    @Command("deleteInformations")
    @NotifyChange("informationsDTOs")
    public void deleteInformations(@BindingParam("object") InformationsDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        informationsDTO = (InformationsDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
            @Override
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    informationsService.deleteData(informationsDTO);
                    showInformationMessagebox("Data Successfully Deleted");
                    BindUtils.postGlobalCommand(null, null, "refreshDataInformations", null);
                } else {
                    System.out.println("Operation Canceled !");
                }
            }
        }
        );
    }

    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }

    public InformationsDTO getInformationsDTO() {
        return informationsDTO;
    }

    public void setInformationsDTO(InformationsDTO informationsDTO) {
        this.informationsDTO = informationsDTO;
    }

    public List<InformationsDTO> getInformationsDTOs() {
        return informationsDTOs;
    }

    public void setInformationsDTOs(List<InformationsDTO> informationsDTOs) {
        this.informationsDTOs = informationsDTOs;
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

    public InformationsService getInformationsService() {
        return informationsService;
    }

    public void setInformationsService(InformationsService informationsService) {
        this.informationsService = informationsService;
    }

    public Media getMediaUploadPicInformations() {
        return mediaUploadPicInformations;
    }

    public void setMediaUploadPicInformations(Media mediaUploadPicInformations) {
        this.mediaUploadPicInformations = mediaUploadPicInformations;
    }

    public String getMediaNameUploadPicInformations() {
        return mediaNameUploadPicInformations;
    }

    public void setMediaNameUploadPicInformations(String mediaNameUploadPicInformations) {
        this.mediaNameUploadPicInformations = mediaNameUploadPicInformations;
    }

    public String getFilepathUploadPicInformations() {
        return filepathUploadPicInformations;
    }

    public void setFilepathUploadPicInformations(String filepathUploadPicInformations) {
        this.filepathUploadPicInformations = filepathUploadPicInformations;
    }

    public String getPathLocationUploadPicInformations() {
        return pathLocationUploadPicInformations;
    }

    public void setPathLocationUploadPicInformations(String pathLocationUploadPicInformations) {
        this.pathLocationUploadPicInformations = pathLocationUploadPicInformations;
    }

    public ListModelList<Status> getStatuses() {
        return new ListModelList<>(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

}
