
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.domain.paymenthistory;

import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.status.StatusPayment;
import java.util.Date;

public class PaymentHistoryBuilder {

    private String idPayment;
    private String totalPayment;
    private StatusPayment statusPayment;
    private String attachment;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public PaymentHistoryBuilder() {
    }

    public PaymentHistoryBuilder setIdPayment(String idPayment) {
        this.idPayment = idPayment;
        return this;
    }

    public PaymentHistoryBuilder setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
        return this;
    }

    public PaymentHistoryBuilder setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
        return this;
    }

    public PaymentHistoryBuilder setAttachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    public PaymentHistoryBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public PaymentHistoryBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public PaymentHistoryBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public PaymentHistoryBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public PaymentHistoryBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public PaymentHistory createPaymentHistory() {
        return new PaymentHistory(idPayment, totalPayment, statusPayment, attachment, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }

}
