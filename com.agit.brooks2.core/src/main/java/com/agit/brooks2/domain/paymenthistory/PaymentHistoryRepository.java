package com.agit.brooks2.domain.paymenthistory;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface PaymentHistoryRepository {

    void saveOrUpdate(PaymentHistory paymentHistory);

    void delete(PaymentHistory paymentHistory);

    PaymentHistory findByID(String idPayment);

    List<PaymentHistory> findAll();

    List<PaymentHistory> findByParams(Map map);
}
