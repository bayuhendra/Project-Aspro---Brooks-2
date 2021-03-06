package com.agit.brooks2.domain.paymenthistory;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.status.StatusPayment;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Zaky
 */
public class PaymentHistory implements EntityObject<PaymentHistory> {

    long id;
    private String idPayment;
    private String totalPayment;
    private StatusPayment statusPayment;
    private String attachment;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public PaymentHistory() {
    }

    public PaymentHistory(String idPayment, String totalPayment, StatusPayment statusPayment, String attachment, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idPayment);
        hash = 37 * hash + Objects.hashCode(this.totalPayment);
        hash = 37 * hash + Objects.hashCode(this.statusPayment);
        hash = 37 * hash + Objects.hashCode(this.attachment);
        hash = 37 * hash + Objects.hashCode(this.createdBy);
        hash = 37 * hash + Objects.hashCode(this.createdDate);
        hash = 37 * hash + Objects.hashCode(this.modifiedBy);
        hash = 37 * hash + Objects.hashCode(this.modifiedDate);
        hash = 37 * hash + Objects.hashCode(this.status);
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
        final PaymentHistory other = (PaymentHistory) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void assignNewPaymentHistory(PaymentHistory paymentHistory) {
        this.idPayment = paymentHistory.idPayment;
        this.totalPayment = paymentHistory.totalPayment;
        this.statusPayment = paymentHistory.statusPayment;
        this.attachment = paymentHistory.attachment;
        this.modifiedBy = paymentHistory.modifiedBy;
        this.modifiedDate = paymentHistory.modifiedDate;
        this.status = paymentHistory.status;
    }

    @Override
    public boolean sameIdentityAs(PaymentHistory other) {
        return this.equals(other);
    }

}
