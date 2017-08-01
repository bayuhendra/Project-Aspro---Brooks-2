package com.agit.brooks2.domain.secondaryunit;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;


public class SecondaryUnitBuilder {
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

    public SecondaryUnitBuilder() {
    }

    public SecondaryUnitBuilder setIdSecondaryUnit(String idSecondaryUnit) {
        this.idSecondaryUnit = idSecondaryUnit;
        return this;
    }

    public SecondaryUnitBuilder setNameSecondaryUnit(String nameSecondaryUnit) {
        this.nameSecondaryUnit = nameSecondaryUnit;
        return this;
    }

    public SecondaryUnitBuilder setDetailSecondaryUnit(String detailSecondaryUnit) {
        this.detailSecondaryUnit = detailSecondaryUnit;
        return this;
    }

    public SecondaryUnitBuilder setDataPhotoSecondaryUnit(byte dataPhotoSecondaryUnit) {
        this.dataPhotoSecondaryUnit = dataPhotoSecondaryUnit;
        return this;
    }

    public SecondaryUnitBuilder setUrlPhotoSecondaryUnit(String urlPhotoSecondaryUnit) {
        this.urlPhotoSecondaryUnit = urlPhotoSecondaryUnit;
        return this;
    }

    public SecondaryUnitBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public SecondaryUnitBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public SecondaryUnitBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public SecondaryUnitBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public SecondaryUnitBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public SecondaryUnit createSecondaryUnit() {
        return new SecondaryUnit(idSecondaryUnit, nameSecondaryUnit, detailSecondaryUnit, dataPhotoSecondaryUnit, urlPhotoSecondaryUnit, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }
    
}
