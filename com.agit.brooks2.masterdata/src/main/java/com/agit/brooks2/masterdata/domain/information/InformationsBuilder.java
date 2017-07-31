/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.domain.information;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class InformationsBuilder {

    private String idNews;
    private String tittleNews;
    private String descriptionNews;
    private Status statusProject;
    private Byte photoInformation;
    private String urlPhotoInformation;
    private Status status;
    private Date createdDate;
    private String createdBy;
    private String modifiedBy;
    private Date modifiedDate;

    public InformationsBuilder() {
    }

    public InformationsBuilder setIdNews(String idNews) {
        this.idNews = idNews;
        return this;
    }

    public InformationsBuilder setTittleNews(String tittleNews) {
        this.tittleNews = tittleNews;
        return this;
    }

    public InformationsBuilder setDescriptionNews(String descriptionNews) {
        this.descriptionNews = descriptionNews;
        return this;
    }

    public InformationsBuilder setStatusProject(Status statusProject) {
        this.statusProject = statusProject;
        return this;
    }

    public InformationsBuilder setPhotoInformation(Byte photoInformation) {
        this.photoInformation = photoInformation;
        return this;
    }

    public InformationsBuilder setUrlPhotoInformation(String urlPhotoInformation) {
        this.urlPhotoInformation = urlPhotoInformation;
        return this;
    }

    public InformationsBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public InformationsBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public InformationsBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public InformationsBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public InformationsBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Informations createInformations() {
        return new Informations(idNews, tittleNews, descriptionNews, statusProject, photoInformation, urlPhotoInformation, status, createdDate, createdBy, modifiedBy, modifiedDate);
    }

}
