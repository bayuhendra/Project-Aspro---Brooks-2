package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;


public class HandOverDTOBuilder {
    private String idHandOver;
    private Date startTime;
    private Date endTime;
    private Date startDate;
    private Date endDate;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public HandOverDTOBuilder() {
    }

    public HandOverDTOBuilder setIdHandOver(String idHandOver) {
        this.idHandOver = idHandOver;
        return this;
    }

    public HandOverDTOBuilder setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public HandOverDTOBuilder setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public HandOverDTOBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public HandOverDTOBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public HandOverDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public HandOverDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public HandOverDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public HandOverDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public HandOverDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public HandOverDTO createHandOverDTO() {
        return new HandOverDTO(idHandOver, startTime, endTime, startDate, endDate, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }
    
}
