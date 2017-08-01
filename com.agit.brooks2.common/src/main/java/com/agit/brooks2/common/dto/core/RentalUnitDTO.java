package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Zaky
 */
public class RentalUnitDTO implements Serializable {

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

    public RentalUnitDTO() {
    }

    public RentalUnitDTO(String idRentalUnit, String nameRentalUnit, String detailRentalUnit, String dataPhotoRentalUnit, String urlPhotoRentalUnit, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
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
    public String toString() {
        return "RentalUnitDTO{" + "idRentalUnit=" + idRentalUnit + ", nameRentalUnit=" + nameRentalUnit + ", detailRentalUnit=" + detailRentalUnit + ", dataPhotoRentalUnit=" + dataPhotoRentalUnit + ", urlPhotoRentalUnit=" + urlPhotoRentalUnit + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + '}';
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
    
    

}
