package com.agit.brooks2.domain.secondaryunit;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Zaky
 */
public class SecondaryUnit implements EntityObject<SecondaryUnit> {

    long id;
    private String idSecondaryUnit;
    private String nameSecondaryUnit;
    private String detailSecondaryUnit;
    private byte dataPhotoSecondaryUnit;
    private String urlPhotoSecondaryUnit;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public SecondaryUnit() {
    }

    public SecondaryUnit(String idSecondaryUnit, String nameSecondaryUnit, String detailSecondaryUnit, byte dataPhotoSecondaryUnit, String urlPhotoSecondaryUnit, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
        this.idSecondaryUnit = idSecondaryUnit;
        this.nameSecondaryUnit = nameSecondaryUnit;
        this.detailSecondaryUnit = detailSecondaryUnit;
        this.dataPhotoSecondaryUnit = dataPhotoSecondaryUnit;
        this.urlPhotoSecondaryUnit = urlPhotoSecondaryUnit;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idSecondaryUnit);
        hash = 31 * hash + Objects.hashCode(this.nameSecondaryUnit);
        hash = 31 * hash + Objects.hashCode(this.detailSecondaryUnit);
        hash = 31 * hash + Objects.hashCode(this.dataPhotoSecondaryUnit);
        hash = 31 * hash + Objects.hashCode(this.urlPhotoSecondaryUnit);
        hash = 31 * hash + Objects.hashCode(this.createdBy);
        hash = 31 * hash + Objects.hashCode(this.createdDate);
        hash = 31 * hash + Objects.hashCode(this.modifiedBy);
        hash = 31 * hash + Objects.hashCode(this.modifiedDate);
        hash = 31 * hash + Objects.hashCode(this.status);
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
        final SecondaryUnit other = (SecondaryUnit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void assignNewSecondaryUnit(SecondaryUnit secondaryUnit) {
        this.idSecondaryUnit = secondaryUnit.idSecondaryUnit;
        this.nameSecondaryUnit = secondaryUnit.nameSecondaryUnit;
        this.detailSecondaryUnit = secondaryUnit.detailSecondaryUnit;
        this.dataPhotoSecondaryUnit = secondaryUnit.dataPhotoSecondaryUnit;
        this.urlPhotoSecondaryUnit = secondaryUnit.urlPhotoSecondaryUnit;
        this.createdBy = secondaryUnit.createdBy;
        this.createdDate = secondaryUnit.createdDate;
        this.modifiedBy = secondaryUnit.modifiedBy;
        this.modifiedDate = secondaryUnit.modifiedDate;
        this.status = secondaryUnit.status;
    }

    public String getIdSecondaryUnit() {
        return idSecondaryUnit;
    }

    public void setIdSecondaryUnit(String idSecondaryUnit) {
        this.idSecondaryUnit = idSecondaryUnit;
    }

    public String getNameSecondaryUnit() {
        return nameSecondaryUnit;
    }

    public void setNameSecondaryUnit(String nameSecondaryUnit) {
        this.nameSecondaryUnit = nameSecondaryUnit;
    }

    public String getDetailSecondaryUnit() {
        return detailSecondaryUnit;
    }

    public void setDetailSecondaryUnit(String detailSecondaryUnit) {
        this.detailSecondaryUnit = detailSecondaryUnit;
    }

    public byte getDataPhotoSecondaryUnit() {
        return dataPhotoSecondaryUnit;
    }

    public void setDataPhotoSecondaryUnit(byte dataPhotoSecondaryUnit) {
        this.dataPhotoSecondaryUnit = dataPhotoSecondaryUnit;
    }
    
    public String getUrlPhotoSecondaryUnit() {
        return urlPhotoSecondaryUnit;
    }

    public void setUrlPhotoSecondaryUnit(String urlPhotoSecondaryUnit) {
        this.urlPhotoSecondaryUnit = urlPhotoSecondaryUnit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    

    @Override
    public boolean sameIdentityAs(SecondaryUnit other) {
        return this.equals(other);
    }
}
