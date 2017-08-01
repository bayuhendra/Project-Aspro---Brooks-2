package com.agit.brooks2.domain.handover;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;


public class HandOverBuilder {
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

    public HandOverBuilder() {
    }

    public HandOverBuilder setIdHandOver(String idHandOver) {
        this.idHandOver = idHandOver;
        return this;
    }

    public HandOverBuilder setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public HandOverBuilder setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public HandOverBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public HandOverBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public HandOverBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public HandOverBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public HandOverBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public HandOverBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public HandOverBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public HandOver createHandOver() {
        return new HandOver(idHandOver, startTime, endTime, startDate, endDate, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }
    
}
