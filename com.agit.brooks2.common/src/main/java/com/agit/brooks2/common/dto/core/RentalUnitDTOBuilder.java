package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;


public class RentalUnitDTOBuilder {
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

    public RentalUnitDTOBuilder() {
    }

    public RentalUnitDTOBuilder setIdRentalUnit(String idRentalUnit) {
        this.idRentalUnit = idRentalUnit;
        return this;
    }

    public RentalUnitDTOBuilder setNameRentalUnit(String nameRentalUnit) {
        this.nameRentalUnit = nameRentalUnit;
        return this;
    }

    public RentalUnitDTOBuilder setDetailRentalUnit(String detailRentalUnit) {
        this.detailRentalUnit = detailRentalUnit;
        return this;
    }

    public RentalUnitDTOBuilder setDataPhotoRentalUnit(byte dataPhotoRentalUnit) {
        this.dataPhotoRentalUnit = dataPhotoRentalUnit;
        return this;
    }

    public RentalUnitDTOBuilder setUrlPhotoRentalUnit(String urlPhotoRentalUnit) {
        this.urlPhotoRentalUnit = urlPhotoRentalUnit;
        return this;
    }

    public RentalUnitDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public RentalUnitDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public RentalUnitDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public RentalUnitDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public RentalUnitDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public RentalUnitDTO createRentalUnitDTO() {
        return new RentalUnitDTO(idRentalUnit, nameRentalUnit, detailRentalUnit, dataPhotoRentalUnit, urlPhotoRentalUnit, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }
    
}
