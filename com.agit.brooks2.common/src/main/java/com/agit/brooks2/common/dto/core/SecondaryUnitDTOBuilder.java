package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;


public class SecondaryUnitDTOBuilder {
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

    public SecondaryUnitDTOBuilder() {
    }

    public SecondaryUnitDTOBuilder setIdSecondaryUnit(String idSecondaryUnit) {
        this.idSecondaryUnit = idSecondaryUnit;
        return this;
    }

    public SecondaryUnitDTOBuilder setNameSecondaryUnit(String nameSecondaryUnit) {
        this.nameSecondaryUnit = nameSecondaryUnit;
        return this;
    }

    public SecondaryUnitDTOBuilder setDetailSecondaryUnit(String detailSecondaryUnit) {
        this.detailSecondaryUnit = detailSecondaryUnit;
        return this;
    }

    public SecondaryUnitDTOBuilder setDataPhotoSecondaryUnit(byte dataPhotoSecondaryUnit) {
        this.dataPhotoSecondaryUnit = dataPhotoSecondaryUnit;
        return this;
    }

    public SecondaryUnitDTOBuilder setUrlPhotoSecondaryUnit(String urlPhotoSecondaryUnit) {
        this.urlPhotoSecondaryUnit = urlPhotoSecondaryUnit;
        return this;
    }

    public SecondaryUnitDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public SecondaryUnitDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public SecondaryUnitDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public SecondaryUnitDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public SecondaryUnitDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public SecondaryUnitDTO createSecondaryUnitDTO() {
        return new SecondaryUnitDTO(idSecondaryUnit, nameSecondaryUnit, detailSecondaryUnit, dataPhotoSecondaryUnit, urlPhotoSecondaryUnit, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }
    
}
