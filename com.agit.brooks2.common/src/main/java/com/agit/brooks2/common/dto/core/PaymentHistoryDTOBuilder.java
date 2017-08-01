/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.status.StatusPayment;
import java.util.Date;

public class PaymentHistoryDTOBuilder {

    private String idPayment;
    private String totalPayment;
    private StatusPayment statusPayment;
    private String attachment;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public PaymentHistoryDTOBuilder() {
    }

    public PaymentHistoryDTOBuilder setIdPayment(String idPayment) {
        this.idPayment = idPayment;
        return this;
    }

    public PaymentHistoryDTOBuilder setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
        return this;
    }

    public PaymentHistoryDTOBuilder setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
        return this;
    }

    public PaymentHistoryDTOBuilder setAttachment(String attachment) {
        this.attachment = attachment;
        return this;
    }

    public PaymentHistoryDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public PaymentHistoryDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public PaymentHistoryDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public PaymentHistoryDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public PaymentHistoryDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public PaymentHistoryDTO createPaymentHistoryDTO() {
        return new PaymentHistoryDTO(idPayment, totalPayment, statusPayment, attachment, createdBy, createdDate, modifiedBy, modifiedDate, status);
    }

}
