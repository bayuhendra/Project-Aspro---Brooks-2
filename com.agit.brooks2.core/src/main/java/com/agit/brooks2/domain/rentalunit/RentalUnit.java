package com.agit.brooks2.domain.rentalunit;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Zaky
 */
public class RentalUnit implements EntityObject<RentalUnit> {

    long id;
    private String idRentalUnit;
    private String nameRentalUnit;
    private String detailRentalUnit;
    private String dataPhotoRentalUnit;
    private String urlPhotoRentalUnit;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public RentalUnit() {
    }

    public RentalUnit(String idRentalUnit, String nameRentalUnit, String detailRentalUnit, String dataPhotoRentalUnit, String urlPhotoRentalUnit, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
        this.idRentalUnit = idRentalUnit;
        this.nameRentalUnit = nameRentalUnit;
        this.detailRentalUnit = detailRentalUnit;
        this.dataPhotoRentalUnit = dataPhotoRentalUnit;
        this.urlPhotoRentalUnit = urlPhotoRentalUnit;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idRentalUnit);
        hash = 29 * hash + Objects.hashCode(this.nameRentalUnit);
        hash = 29 * hash + Objects.hashCode(this.detailRentalUnit);
        hash = 29 * hash + Objects.hashCode(this.dataPhotoRentalUnit);
        hash = 29 * hash + Objects.hashCode(this.urlPhotoRentalUnit);
        hash = 29 * hash + Objects.hashCode(this.createdBy);
        hash = 29 * hash + Objects.hashCode(this.createdDate);
        hash = 29 * hash + Objects.hashCode(this.modifiedBy);
        hash = 29 * hash + Objects.hashCode(this.modifiedDate);
        hash = 29 * hash + Objects.hashCode(this.status);
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
        final RentalUnit other = (RentalUnit) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public void assignNewRentalUnit (RentalUnit rentalUnit) {
        this.idRentalUnit = rentalUnit.idRentalUnit;
        this.nameRentalUnit = rentalUnit.nameRentalUnit;
        this.detailRentalUnit = rentalUnit.detailRentalUnit;
        this.dataPhotoRentalUnit = rentalUnit.dataPhotoRentalUnit;
        this.urlPhotoRentalUnit = rentalUnit.urlPhotoRentalUnit;
        this.createdBy = rentalUnit.createdBy;
        this.createdDate = rentalUnit.createdDate;
        this.modifiedBy = rentalUnit.modifiedBy;
        this.modifiedDate = rentalUnit.modifiedDate;
        this.status = rentalUnit.status;
    }

    public String getIdRentalUnit() {
        return idRentalUnit;
    }

    public void setIdRentalUnit(String idRentalUnit) {
        this.idRentalUnit = idRentalUnit;
    }

    public String getNameRentalUnit() {
        return nameRentalUnit;
    }

    public void setNameRentalUnit(String nameRentalUnit) {
        this.nameRentalUnit = nameRentalUnit;
    }

    public String getDetailRentalUnit() {
        return detailRentalUnit;
    }

    public void setDetailRentalUnit(String detailRentalUnit) {
        this.detailRentalUnit = detailRentalUnit;
    }

    public String getDataPhotoRentalUnit() {
        return dataPhotoRentalUnit;
    }

    public void setDataPhotoRentalUnit(String dataPhotoRentalUnit) {
        this.dataPhotoRentalUnit = dataPhotoRentalUnit;
    }

    public String getUrlPhotoRentalUnit() {
        return urlPhotoRentalUnit;
    }

    public void setUrlPhotoRentalUnit(String urlPhotoRentalUnit) {
        this.urlPhotoRentalUnit = urlPhotoRentalUnit;
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
    public boolean sameIdentityAs(RentalUnit other) {
        return this.equals(other);
    }

}
