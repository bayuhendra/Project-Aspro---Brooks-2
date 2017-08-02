package com.agit.brooks2.masterdata.domain.information;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author 3AD
 */
public class Informations implements EntityObject<Informations> {

    long id;
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

    public Informations() {
    }

    public Informations(String idNews, String tittleNews, String descriptionNews, String statusProject, byte photoInformation, String urlPhotoInformation, Status status, Date createdDate, String createdBy, String modifiedBy, Date modifiedDate) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUrlPhotoInformation() {
        return urlPhotoInformation;
    }

    public byte getPhotoInformation() {
        return photoInformation;
    }

    public void setPhotoInformation(byte photoInformation) {
        this.photoInformation = photoInformation;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idNews);
        hash = 71 * hash + Objects.hashCode(this.tittleNews);
        hash = 71 * hash + Objects.hashCode(this.descriptionNews);
        hash = 71 * hash + Objects.hashCode(this.statusProject);
        hash = 71 * hash + Objects.hashCode(this.photoInformation);
        hash = 71 * hash + Objects.hashCode(this.urlPhotoInformation);
        hash = 71 * hash + Objects.hashCode(this.status);
        hash = 71 * hash + Objects.hashCode(this.createdDate);
        hash = 71 * hash + Objects.hashCode(this.createdBy);
        hash = 71 * hash + Objects.hashCode(this.modifiedBy);
        hash = 71 * hash + Objects.hashCode(this.modifiedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Informations other = (Informations) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void assignNewInformations(Informations informations) {
        this.idNews = informations.idNews;
        this.tittleNews = informations.tittleNews;
        this.descriptionNews = informations.descriptionNews;
        this.statusProject = informations.statusProject;
        this.photoInformation = informations.photoInformation;
        this.urlPhotoInformation = informations.urlPhotoInformation;
        this.status = informations.status;
        this.createdDate = informations.createdDate;
        this.createdBy = informations.createdBy;
        this.modifiedBy = informations.modifiedBy;
        this.modifiedDate = informations.modifiedDate;
    }

    @Override
    public boolean sameIdentityAs(Informations other) {
        return this.equals(other);
    }

}
