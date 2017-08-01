package com.agit.brooks2.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.core.PaymentHistoryDTO;
import com.agit.brooks2.common.dto.core.PaymentHistoryDTOBuilder;
import com.agit.brooks2.domain.paymenthistory.PaymentHistory;
import com.agit.brooks2.domain.paymenthistory.PaymentHistoryBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3AD
 */
public class PaymentHistoryDTOAssembler implements IObjectAssembler<PaymentHistory, PaymentHistoryDTO> {

    @Override
    public PaymentHistoryDTO toDTO(PaymentHistory domainObject) {
        return new PaymentHistoryDTOBuilder()
                .setIdPayment(domainObject.getIdPayment())
                .setTotalPayment(domainObject.getTotalPayment())
                .setAttachment(domainObject.getAttachment())
                .setStatusPayment(domainObject.getStatusPayment())
                .setStatus(domainObject.getStatus())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .createPaymentHistoryDTO();
    }

    @Override
    public PaymentHistory toDomain(PaymentHistoryDTO dtoObject) {
        return new PaymentHistoryBuilder()
                .setIdPayment(dtoObject.getIdPayment())
                .setTotalPayment(dtoObject.getTotalPayment())
                .setAttachment(dtoObject.getAttachment())
                .setStatusPayment(dtoObject.getStatusPayment())
                .setStatus(dtoObject.getStatus())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .createPaymentHistory();
    }

    public List<PaymentHistory> toDomains(List<PaymentHistoryDTO> arg0) {
        List<PaymentHistory> res = new ArrayList<>();
        for (PaymentHistoryDTO p : arg0) {
            res.add(this.toDomain(p));
        }
        return res;
    }

    public List<PaymentHistoryDTO> toDTOs(List<PaymentHistory> arg0) {
        List<PaymentHistoryDTO> res = new ArrayList<>();
        for (PaymentHistory p : arg0) {
            res.add(this.toDTO(p));
        }
        return res;
    }
}
