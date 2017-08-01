package com.agit.brooks2.domain.rentalunit;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;


public class RentalUnitBuilder {
    private String idRentalUnit;
    private String nameRentalUnit;
    private String detailRentalUnit;
    private byte dataPhotoRentalUnit;
    private String urlPhotoRentalUnit;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public RentalUnitBuilder() {
    }

    public RentalUnitBuilder setIdRentalUnit(String idRentalUnit) {
        this.idRentalUnit = idRentalUnit;
        return this;
    }

    public RentalUnitBuilder setNameRentalUnit(String nameRentalUnit) {
        this.nameRentalUnit = nameRentalUnit;
        return this;
    }

    public RentalUnitBuilder setDetailRentalUnit(String detailRentalUnit) {
        this.detailRentalUnit = detailRentalUnit;
        return this;
    }

    public RentalUnitBuilder setDataPhotoRentalUnit(byte dataPhotoRentalUnit) {
        this.dataPhotoRentalUnit = dataPhotoRentalUnit;
        return this;
    }

    public RentalUnitBuilder setUrlPhotoRentalUnit(String urlPhotoRentalUnit) {
        this.urlPhotoRentalUnit = urlPhotoRentalUnit;
        return this;
    }

    public RentalUnitBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public RentalUnitBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public RentalUnitBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public RentalUnitBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public RentalUnitBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public RentalUnit createRentalUnit() {
        return new RentalUnit(idRentalUnit, nameRentalUnit, detailRentalUnit, dataPhotoRentalUnit, urlPhotoRentalUnit, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }
    
}
