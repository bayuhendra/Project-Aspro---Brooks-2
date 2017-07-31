/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class ProjectDTOBuilder {

    private String idProject;
    private String nameProject;
    private Status status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public ProjectDTOBuilder() {
    }

    public ProjectDTOBuilder setIdProject(String idProject) {
        this.idProject = idProject;
        return this;
    }

    public ProjectDTOBuilder setNameProject(String nameProject) {
        this.nameProject = nameProject;
        return this;
    }

    public ProjectDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ProjectDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public ProjectDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ProjectDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public ProjectDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public ProjectDTO createProjectDTO() {
        return new ProjectDTO(idProject, nameProject, status, createdBy, createdDate, modifiedBy, modifiedDate);
    }

}
