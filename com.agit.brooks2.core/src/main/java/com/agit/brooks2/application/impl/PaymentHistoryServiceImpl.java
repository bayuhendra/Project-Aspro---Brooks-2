package com.agit.brooks2.application.impl;

import com.agit.brooks2.common.application.PaymentHistoryService;
import com.agit.brooks2.common.dto.core.PaymentHistoryDTO;
import com.agit.brooks2.domain.paymenthistory.PaymentHistory;
import com.agit.brooks2.domain.paymenthistory.PaymentHistoryBuilder;
import com.agit.brooks2.domain.paymenthistory.PaymentHistoryRepository;
import com.agit.brooks2.interfaces.web.facade.dto.assembler.PaymentHistoryDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import com.agit.brooks2.shared.status.StatusPayment;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;

/**
 *
 * @author 3AD
 */
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private PaymentHistoryRepository paymentHistoryRepository;
    private PaymentHistoryDTOAssembler paymentHistoryDTOAssembler;

    public void setPaymentHistoryRepository(PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    public void setPaymentHistoryDTOAssembler(PaymentHistoryDTOAssembler paymentHistoryDTOAssembler) {
        this.paymentHistoryDTOAssembler = paymentHistoryDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(PaymentHistoryDTO paymentHistory) {
        PaymentHistory p = paymentHistoryRepository.findByID(paymentHistory.getIdPayment());

        if (p == null) {
            p = paymentHistoryDTOAssembler.toDomain(paymentHistory);
        } else {
            PaymentHistory newPaymentHistory = paymentHistoryDTOAssembler.toDomain(paymentHistory);
            p.assignNewPaymentHistory(newPaymentHistory);
        }
        paymentHistoryRepository.saveOrUpdate(p);
    }

    @Override
    public void deleteData(PaymentHistoryDTO paymentHistory) {
        PaymentHistory p = paymentHistoryDTOAssembler.toDomain(paymentHistory);
        paymentHistoryRepository.delete(p);
    }

    @Override
    public PaymentHistoryDTO getDummyData() {
        PaymentHistory paymentHistory = new PaymentHistoryBuilder()
                .setIdPayment("P001")
                .setTotalPayment("Total Payment")
                .setAttachment("Attachment")
                .setStatusPayment(StatusPayment.PAID)
                .setStatus(Status.ACTIVE)
                .setCreatedBy("System")
                .setCreatedDate(new Date())
                .setModifiedBy("System")
                .setModifiedDate(new Date())
                .createPaymentHistory();
        return paymentHistoryDTOAssembler.toDTO(paymentHistory);
    }

    @Override
    public PaymentHistoryDTO findByID(String idPayment) {
        Validate.notNull(paymentHistoryRepository);
        PaymentHistory p = (PaymentHistory) paymentHistoryRepository.findByID(idPayment);
        if (p != null) {
            return paymentHistoryDTOAssembler.toDTO(p);
        }
        return null;
    }

    @Override
    public List<PaymentHistoryDTO> findAll() {
        Validate.notNull(paymentHistoryRepository);
        return paymentHistoryDTOAssembler.toDTOs(paymentHistoryRepository.findAll());
    }

    @Override
    public List<PaymentHistoryDTO> findByParams(Map map) {
        Validate.notNull(paymentHistoryRepository);
        List<PaymentHistory> listPaymentHistory = paymentHistoryRepository.findByParams(map);
        if (listPaymentHistory != null) {
            return (List<PaymentHistoryDTO>) paymentHistoryDTOAssembler.toDTOs(listPaymentHistory);
        }
        return null;
    }

}
