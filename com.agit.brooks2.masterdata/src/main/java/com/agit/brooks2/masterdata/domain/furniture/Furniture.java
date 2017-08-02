package com.agit.brooks2.masterdata.domain.furniture;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author 3AD
 */
public class Furniture implements EntityObject<Furniture> {

    long id;
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

    public Furniture() {
    }

    public Furniture(String idFurniture, String nameFurniture, byte photoFurniture, String urlPhoto, Status status, String descriptionFurniture, Date createdDate, String createdBy, String modifiedBy, Date modifiedDate) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public byte getPhotoFurniture() {
        return photoFurniture;
    }

    public void setPhotoFurniture(byte photoFurniture) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.idFurniture);
        hash = 89 * hash + Objects.hashCode(this.nameFurniture);
        hash = 89 * hash + Objects.hashCode(this.photoFurniture);
        hash = 89 * hash + Objects.hashCode(this.urlPhoto);
        hash = 89 * hash + Objects.hashCode(this.status);
        hash = 89 * hash + Objects.hashCode(this.descriptionFurniture);
        hash = 89 * hash + Objects.hashCode(this.createdDate);
        hash = 89 * hash + Objects.hashCode(this.createdBy);
        hash = 89 * hash + Objects.hashCode(this.modifiedBy);
        hash = 89 * hash + Objects.hashCode(this.modifiedDate);
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
        final Furniture other = (Furniture) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void assignNewFurniture(Furniture furniture) {
        this.idFurniture = furniture.idFurniture;
        this.nameFurniture = furniture.nameFurniture;
        this.photoFurniture = furniture.photoFurniture;
        this.urlPhoto = furniture.urlPhoto;
        this.status = furniture.status;
        this.descriptionFurniture = furniture.descriptionFurniture;
        this.createdDate = furniture.createdDate;
        this.createdBy = furniture.createdBy;
        this.modifiedBy = furniture.modifiedBy;
        this.modifiedDate = furniture.modifiedDate;
    }

    @Override
    public boolean sameIdentityAs(Furniture other) {
        return this.equals(other);
    }

}
