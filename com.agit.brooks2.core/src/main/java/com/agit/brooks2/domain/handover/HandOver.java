package com.agit.brooks2.domain.handover;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Zaky
 */
public class HandOver implements EntityObject<HandOver> {

    long id;
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

    public HandOver() {
    }

    public HandOver(String idHandOver, Date startTime, Date endTime, Date startDate, Date endDate, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idHandOver);
        hash = 17 * hash + Objects.hashCode(this.startTime);
        hash = 17 * hash + Objects.hashCode(this.endTime);
        hash = 17 * hash + Objects.hashCode(this.startDate);
        hash = 17 * hash + Objects.hashCode(this.endDate);
        hash = 17 * hash + Objects.hashCode(this.createdBy);
        hash = 17 * hash + Objects.hashCode(this.createdDate);
        hash = 17 * hash + Objects.hashCode(this.modifiedBy);
        hash = 17 * hash + Objects.hashCode(this.modifiedDate);
        hash = 17 * hash + Objects.hashCode(this.status);
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
        final HandOver other = (HandOver) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public void assignNewHandOver (HandOver handOver) {
        this.idHandOver = handOver.idHandOver;
        this.startTime = handOver.startTime;
        this.endTime = handOver.endTime;
        this.startDate = handOver.startDate;
        this.endDate = handOver.endDate;
        this.createdBy = handOver.createdBy;
        this.createdDate = handOver.createdDate;
        this.modifiedBy = handOver.modifiedBy;
        this.modifiedDate = handOver.modifiedDate;
        this.status = handOver.status;
    }
    
    @Override
    public boolean sameIdentityAs(HandOver other) {
        return this.equals(other);
    }

}
