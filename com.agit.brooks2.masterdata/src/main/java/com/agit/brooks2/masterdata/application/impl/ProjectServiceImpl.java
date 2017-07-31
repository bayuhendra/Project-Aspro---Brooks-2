/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.application.impl;

import com.agit.brooks2.common.application.ProjectService;
import com.agit.brooks2.common.dto.masterdata.ProjectDTO;
import com.agit.brooks2.masterdata.domain.project.Project;
import com.agit.brooks2.masterdata.domain.project.ProjectBuilder;
import com.agit.brooks2.masterdata.domain.project.ProjectRepository;
import com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler.ProjectDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author 3AD
 */
@Controller
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ProjectDTOAssembler projectDTOAssembler;

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void setProjectDTOAssembler(ProjectDTOAssembler projectDTOAssembler) {
        this.projectDTOAssembler = projectDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(ProjectDTO project) {
        Project p = projectRepository.findByID(project.getIdProject());

        if (p == null) {
            p = projectDTOAssembler.toDomain(project);
        } else {
            Project newProject = projectDTOAssembler.toDomain(project);
            p.assignNewProject(newProject);
        }
        projectRepository.saveOrUpdate(p);
    }

    @Override
    public void deleteData(ProjectDTO project) {
        Project p = projectDTOAssembler.toDomain(project);
        projectRepository.delete(p);
    }

    @Override
    public ProjectDTO getDummyData() {
        Project project = new ProjectBuilder()
                .setIdProject("P001")
                .setNameProject("AAA")
                .setStatus(Status.ACTIVE)
                .setCreatedBy("System")
                .setCreatedDate(new Date())
                .setModifiedBy("System")
                .setModifiedDate(new Date())
                .createProject();
        return projectDTOAssembler.toDTO(project);
    }

    @Override
    public ProjectDTO findByID(String idProject) {
        Validate.notNull(projectRepository);
        Project p = (Project) projectRepository.findByID(idProject);
        if (p != null) {
            return projectDTOAssembler.toDTO(p);
        }
        return null;
    }

    @Override
    public List<ProjectDTO> findAll() {
        Validate.notNull(projectRepository);
        return projectDTOAssembler.toDTOs(projectRepository.findAll());
    }

    @Override
    public List<ProjectDTO> findByParams(Map map) {
        Validate.notNull(projectRepository);
        List<Project> listProject = projectRepository.findByParams(map);
        if (listProject != null) {
            return (List<ProjectDTO>) projectDTOAssembler.toDTOs(listProject);
        }
        return null;
    }

}
