package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Zaky
 */
public class HandOverDTO implements Serializable {

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

    public HandOverDTO() {
    }

    public HandOverDTO(String idHandOver, Date startTime, Date endTime, Date startDate, Date endDate, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
        this.idHandOver = idHandOver;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "HandOverDTO{" + "idHandOver=" + idHandOver + ", startTime=" + startTime + ", endTime=" + endTime + ", startDate=" + startDate + ", endDate=" + endDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + '}';
    }

    public String getIdHandOver() {
        return idHandOver;
    }

    public void setIdHandOver(String idHandOver) {
        this.idHandOver = idHandOver;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
