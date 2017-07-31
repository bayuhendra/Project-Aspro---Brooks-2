package com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.masterdata.ProjectDTO;
import com.agit.brooks2.common.dto.masterdata.ProjectDTOBuilder;
import com.agit.brooks2.masterdata.domain.project.Project;
import com.agit.brooks2.masterdata.domain.project.ProjectBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3AD
 */
public class ProjectDTOAssembler implements IObjectAssembler<Project, ProjectDTO> {

    @Override
    public ProjectDTO toDTO(Project domainObject) {
        return new ProjectDTOBuilder()
                .setIdProject(domainObject.getIdProject())
                .setNameProject(domainObject.getNameProject())
                .setStatus(domainObject.getStatus())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .createProjectDTO();

    }

    @Override
    public Project toDomain(ProjectDTO dtoObject) {
        return new ProjectBuilder()
                .setIdProject(dtoObject.getIdProject())
                .setNameProject(dtoObject.getNameProject())
                .setStatus(dtoObject.getStatus())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .createProject();
    }

    public List<Project> toDomains(List<ProjectDTO> arg0) {
        List<Project> res = new ArrayList<>();
        for (ProjectDTO p : arg0) {
            res.add(this.toDomain(p));
        }
        return res;
    }

    public List<ProjectDTO> toDTOs(List<Project> arg0) {
        List<ProjectDTO> res = new ArrayList<>();
        for (Project p : arg0) {
            res.add(this.toDTO(p));
        }
        return res;
    }

}
