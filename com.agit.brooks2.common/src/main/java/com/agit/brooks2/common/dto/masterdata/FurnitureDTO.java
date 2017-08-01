/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 3AD
 */
public class FurnitureDTO implements Serializable {

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

    public FurnitureDTO() {
    }

    public FurnitureDTO(String idFurniture, String nameFurniture, Byte photoFurniture, String urlPhoto, Status status, String descriptionFurniture, Date createdDate, String createdBy, String modifiedBy, Date modifiedDate) {
        this.idFurniture = idFurniture;
        this.nameFurniture = nameFurniture;
        this.photoFurniture = photoFurniture;
        this.urlPhoto = urlPhoto;
        this.status = status;
        this.descriptionFurniture = descriptionFurniture;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public String getIdFurniture() {
        return idFurniture;
    }

    public void setIdFurniture(String idFurniture) {
        this.idFurniture = idFurniture;
    }

    public String getNameFurniture() {
        return nameFurniture;
    }

    public void setNameFurniture(String nameFurniture) {
        this.nameFurniture = nameFurniture;
    }

    public Byte getPhotoFurniture() {
        return photoFurniture;
    }

    public void setPhotoFurniture(Byte photoFurniture) {
        this.photoFurniture = photoFurniture;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescriptionFurniture() {
        return descriptionFurniture;
    }

    public void setDescriptionFurniture(String descriptionFurniture) {
        this.descriptionFurniture = descriptionFurniture;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "FurnitureDTO{" + "idFurniture=" + idFurniture + ", nameFurniture=" + nameFurniture + ", photoFurniture=" + photoFurniture + ", urlPhoto=" + urlPhoto + ", status=" + status + ", descriptionFurniture=" + descriptionFurniture + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + '}';
    }

}
