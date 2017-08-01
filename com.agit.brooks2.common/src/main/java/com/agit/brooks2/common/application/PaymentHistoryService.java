package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.core.PaymentHistoryDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface PaymentHistoryService {

    void SaveOrUpdate(PaymentHistoryDTO paymentHistory);

    void deleteData(PaymentHistoryDTO paymentHistory);

    PaymentHistoryDTO getDummyData();

    PaymentHistoryDTO findByID(String idPayment);

    List<PaymentHistoryDTO> findAll();

    List<PaymentHistoryDTO> findByParams(Map map);
}
