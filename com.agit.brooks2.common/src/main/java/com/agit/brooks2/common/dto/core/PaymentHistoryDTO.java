package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.status.StatusPayment;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 3AD
 */
public class PaymentHistoryDTO implements Serializable {

    private String idPayment;
    private String totalPayment;
    private StatusPayment statusPayment;
    private String attachment;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public PaymentHistoryDTO() {
    }

    public PaymentHistoryDTO(String idPayment, String totalPayment, StatusPayment statusPayment, String attachment, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
        this.idPayment = idPayment;
        this.totalPayment = totalPayment;
        this.statusPayment = statusPayment;
        this.attachment = attachment;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    public String getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(String idPayment) {
        this.idPayment = idPayment;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
    public String toString() {
        return "PaymentHistoryDTO{" + "idPayment=" + idPayment + ", totalPayment=" + totalPayment + ", statusPayment=" + statusPayment + ", attachment=" + attachment + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + '}';
    }

}
