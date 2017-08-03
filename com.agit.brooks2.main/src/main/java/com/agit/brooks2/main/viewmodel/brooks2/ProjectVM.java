package com.agit.brooks2.main.viewmodel.brooks2;

import com.agit.brooks2.common.application.ProjectService;
import com.agit.brooks2.common.dto.masterdata.ProjectDTO;
import com.agit.brooks2.common.dto.masterdata.ProjectDTOBuilder;
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
public class ProjectVM {

    @WireVariable
    ProjectService projectService;

    private ProjectDTO projectDTO = new ProjectDTO();
    private List<ProjectDTO> projectDTOs = new ArrayList<>();

    private ListModelList<Status> statuses;

    private String idProject;
    private String nameProject;
    private Status status;

    private PageNavigation previous;
    private int pageSize = 10;

    @Init
    public void init(
            @ExecutionArgParam("previous") PageNavigation previous,
            @ExecutionArgParam("projectDTO") ProjectDTO projects) {

        /* Load Data */
        initData();

        /* Check Validity */
        checkValidity(previous, projects);
    }

    private void initData() {

        projectDTOs = projectService.findAll();
        if (projectDTOs.isEmpty()) {
            projectDTOs = Collections.emptyList();
        }
    }

    private void checkValidity(PageNavigation previous, ProjectDTO project) {
        if (project == null) {
            ListModelList<ProjectDTO> parameterList = new ListModelList<>(projectService.findAll());
            String idProject = "";
            if (parameterList.isEmpty()) {
                idProject = "Pro0001";
            } else {
                idProject = getLatestObjectID(parameterList, "idProject");
            }
            projectDTO = new ProjectDTOBuilder()
                    .setIdProject(idProject)
                    .setCreatedBy(SecurityUtil.getUserName())
                    .setCreatedDate(new Date())
                    .createProjectDTO();
        } else {
            this.projectDTO = project;
            idProject = projectDTO.getIdProject();
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
    @NotifyChange("projectDTOs")
    public void refreshDataProject() {
        projectDTOs = projectService.findAll();
    }

    public int checkCountParameter(int count, Object obj) {
        if (StringUtil.hasValue(obj)) {
            count += 1;
        }
        return count;
    }

    /* =================== for dashboard functionality =================== */
    @Command("buttonSearchProject")
    @NotifyChange("projectDTOs")
    public void buttonSearchProject(@ContextParam(ContextType.VIEW) Window window) {
        int count = 0;
        Map params = new HashMap();
        params.put("idProject", idProject);
        count = checkCountParameter(count, idProject);
        params.put("nameProject", nameProject);
        count = checkCountParameter(count, nameProject);
        params.put("status", status);
        count = checkCountParameter(count, status);
        if (count < 1) {
            Messagebox.show("At least input 1 or more search parameter", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }
        projectDTOs = projectService.findByParams(params);
    }

    @Command("buttonFormCreateProject")
    @NotifyChange("projectDTO")
    public void buttonFormCreateProject(@BindingParam("object") ProjectDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/setup_project/create_project.zul", window, params);
    }

    @Command("buttonDetailProject")
    @NotifyChange("project")
    public void buttonDetailProject(@BindingParam("object") ProjectDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectDTO", obj);
        CommonViewModel.navigateToWithoutDetach("/brooks2/admin/setup_project/create_project.zul", window, params);
    }

    @Command("buttonDeleteProject")
    @NotifyChange("projectDTOs")
    public void buttonDeleteProject(@BindingParam("object") ProjectDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        projectDTO = (ProjectDTO) obj;
        Messagebox.show("Are you sure to delete this data?", "Confirmation",
                Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) throws InterruptedException {
                        if (evt.getName().equals("onOK")) {
                            projectService.deleteData(projectDTO);
                            showInformationMessagebox("Data Successfully Deleted");
                            BindUtils.postGlobalCommand(null, null, "refreshDataProject", null);
                        } else {
                            System.out.println("Operation Canceled !");
                        }
                    }
                }
        );
    }

    /* =================== for create functionality =================== */
    @Command("buttonBackCreateProject")
    @NotifyChange("projectDTO")
    public void buttonBackCreateProject(@BindingParam("object") ProjectDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        window.detach();
    }

    @Command("buttonSaveDataProject")
    @NotifyChange("projectDTO")
    public void buttonSaveDataProject(@BindingParam("object") ProjectDTO obj, @ContextParam(ContextType.VIEW) Window window) {
        projectService.SaveOrUpdate(projectDTO);
        showInformationMessagebox("Data Saved");
        BindUtils.postGlobalCommand(null, null, "refreshDataProject", null);
        window.detach();
    }

    /* ============= Getter Setter ============= */
    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public List<ProjectDTO> getProjectDTOs() {
        return projectDTOs;
    }

    public void setProjectDTOs(List<ProjectDTO> projectDTOs) {
        this.projectDTOs = projectDTOs;
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

    public ListModelList<Status> getStatuses() {
        return new ListModelList<>(Status.values());
    }

    public void setStatuses(ListModelList<Status> statuses) {
        this.statuses = statuses;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
