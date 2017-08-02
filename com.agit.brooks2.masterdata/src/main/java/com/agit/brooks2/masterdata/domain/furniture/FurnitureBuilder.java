/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.domain.furniture;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class FurnitureBuilder {

    private String idFurniture;
    private String nameFurniture;
    private byte photoFurniture;
    private String urlPhoto;
    private Status status;
    private String descriptionFurniture;
    private Date createdDate;
    private String createdBy;
    private String modifiedBy;
    private Date modifiedDate;

    public FurnitureBuilder() {
    }

    public FurnitureBuilder setIdFurniture(String idFurniture) {
        this.idFurniture = idFurniture;
        return this;
    }

    public FurnitureBuilder setNameFurniture(String nameFurniture) {
        this.nameFurniture = nameFurniture;
        return this;
    }

    public FurnitureBuilder setPhotoFurniture(byte photoFurniture) {
        this.photoFurniture = photoFurniture;
        return this;
    }

    public FurnitureBuilder setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
        return this;
    }

    public FurnitureBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public FurnitureBuilder setDescriptionFurniture(String descriptionFurniture) {
        this.descriptionFurniture = descriptionFurniture;
        return this;
    }

    public FurnitureBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public FurnitureBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public FurnitureBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public FurnitureBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Furniture createFurniture() {
        return new Furniture(idFurniture, nameFurniture, photoFurniture, urlPhoto, status, descriptionFurniture, createdDate, createdBy, modifiedBy, modifiedDate);
    }

}
