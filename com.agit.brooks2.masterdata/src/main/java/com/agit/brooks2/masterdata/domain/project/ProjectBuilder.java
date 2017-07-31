/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.domain.project;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class ProjectBuilder {

    private String idProject;
    private String nameProject;
    private Status status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public ProjectBuilder() {
    }

    public ProjectBuilder setIdProject(String idProject) {
        this.idProject = idProject;
        return this;
    }

    public ProjectBuilder setNameProject(String nameProject) {
        this.nameProject = nameProject;
        return this;
    }

    public ProjectBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ProjectBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public ProjectBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ProjectBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public ProjectBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Project createProject() {
        return new Project(idProject, nameProject, status, createdBy, createdDate, modifiedBy, modifiedDate);
    }

}
