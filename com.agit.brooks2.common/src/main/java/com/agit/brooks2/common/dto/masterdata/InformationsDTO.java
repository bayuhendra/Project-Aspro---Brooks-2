package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 3AD
 */
public class InformationsDTO implements Serializable {

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

    public InformationsDTO() {
    }

    public InformationsDTO(String idNews, String tittleNews, String descriptionNews, String statusProject, byte photoInformation, String urlPhotoInformation, Status status, Date createdDate, String createdBy, String modifiedBy, Date modifiedDate) {
        this.idNews = idNews;
        this.tittleNews = tittleNews;
        this.descriptionNews = descriptionNews;
        this.statusProject = statusProject;
        this.photoInformation = photoInformation;
        this.urlPhotoInformation = urlPhotoInformation;
        this.status = status;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }

    public String getTittleNews() {
        return tittleNews;
    }

    public void setTittleNews(String tittleNews) {
        this.tittleNews = tittleNews;
    }

    public String getDescriptionNews() {
        return descriptionNews;
    }

    public void setDescriptionNews(String descriptionNews) {
        this.descriptionNews = descriptionNews;
    }

    public String getStatusProject() {
        return statusProject;
    }

    public void setStatusProject(String statusProject) {
        this.statusProject = statusProject;
    }

    public byte getPhotoInformation() {
        return photoInformation;
    }

    public void setPhotoInformation(byte photoInformation) {
        this.photoInformation = photoInformation;
    }

    public String getUrlPhotoInformation() {
        return urlPhotoInformation;
    }

    public void setUrlPhotoInformation(String urlPhotoInformation) {
        this.urlPhotoInformation = urlPhotoInformation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return "InformationsDTO{" + "idNews=" + idNews + ", tittleNews=" + tittleNews + ", descriptionNews=" + descriptionNews + ", statusProject=" + statusProject + ", photoInformation=" + photoInformation + ", urlPhotoInformation=" + urlPhotoInformation + ", status=" + status + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + '}';
    }

}
