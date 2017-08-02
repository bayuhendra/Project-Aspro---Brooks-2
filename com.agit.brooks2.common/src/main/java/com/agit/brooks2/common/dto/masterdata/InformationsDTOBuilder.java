/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class InformationsDTOBuilder {

    private String idNews;
    private String tittleNews;
    private String descriptionNews;
    private String statusProject;
    private byte photoInformation;
    private String urlPhotoInformation;
    private Status status;
    private Date createdDate;
    private String createdBy;
    private String modifiedBy;
    private Date modifiedDate;

    public InformationsDTOBuilder() {
    }

    public InformationsDTOBuilder setIdNews(String idNews) {
        this.idNews = idNews;
        return this;
    }

    public InformationsDTOBuilder setTittleNews(String tittleNews) {
        this.tittleNews = tittleNews;
        return this;
    }

    public InformationsDTOBuilder setDescriptionNews(String descriptionNews) {
        this.descriptionNews = descriptionNews;
        return this;
    }

    public InformationsDTOBuilder setStatusProject(String statusProject) {
        this.statusProject = statusProject;
        return this;
    }

    public InformationsDTOBuilder setPhotoInformation(byte photoInformation) {
        this.photoInformation = photoInformation;
        return this;
    }

    public InformationsDTOBuilder setUrlPhotoInformation(String urlPhotoInformation) {
        this.urlPhotoInformation = urlPhotoInformation;
        return this;
    }

    public InformationsDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public InformationsDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public InformationsDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public InformationsDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public InformationsDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public InformationsDTO createInformationsDTO() {
        return new InformationsDTO(idNews, tittleNews, descriptionNews, statusProject, photoInformation, urlPhotoInformation, status, createdDate, createdBy, modifiedBy, modifiedDate);
    }

}
