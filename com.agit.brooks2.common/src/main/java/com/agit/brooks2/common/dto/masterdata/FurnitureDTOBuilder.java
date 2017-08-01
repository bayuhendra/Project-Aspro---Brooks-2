/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class FurnitureDTOBuilder {

    private String idFurniture;
    private String nameFurniture;
    private Byte photoFurniture;
    private String urlPhoto;
    private Status status;
    private String descriptionFurniture;
    private Date createdDate;
    private String createdBy;
    private String modifiedBy;
    private Date modifiedDate;

    public FurnitureDTOBuilder() {
    }

    public FurnitureDTOBuilder setIdFurniture(String idFurniture) {
        this.idFurniture = idFurniture;
        return this;
    }

    public FurnitureDTOBuilder setNameFurniture(String nameFurniture) {
        this.nameFurniture = nameFurniture;
        return this;
    }

    public FurnitureDTOBuilder setPhotoFurniture(Byte photoFurniture) {
        this.photoFurniture = photoFurniture;
        return this;
    }

    public FurnitureDTOBuilder setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
        return this;
    }

    public FurnitureDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public FurnitureDTOBuilder setDescriptionFurniture(String descriptionFurniture) {
        this.descriptionFurniture = descriptionFurniture;
        return this;
    }

    public FurnitureDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public FurnitureDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public FurnitureDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public FurnitureDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public FurnitureDTO createFurnitureDTO() {
        return new FurnitureDTO(idFurniture, nameFurniture, photoFurniture, urlPhoto, status, descriptionFurniture, createdDate, createdBy, modifiedBy, modifiedDate);
    }

}
