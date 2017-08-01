package com.agit.brooks2.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.domain.paymenthistory.PaymentHistory;
import com.agit.brooks2.domain.paymenthistory.PaymentHistoryRepository;
import com.agit.brooks2.util.StringUtil;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 3AD
 */
@Repository
@Transactional
public class PaymentHistoryHibernateRepository extends HibernateRepository implements PaymentHistoryRepository {

    @Override
    public void saveOrUpdate(PaymentHistory paymentHistory) {
        getSession().saveOrUpdate(paymentHistory);
    }

    @Override
    public void delete(PaymentHistory paymentHistory) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.domain.paymenthistory.PaymentHistory where idPayment = :ipj");
        query.setParameter("ipj", paymentHistory.getIdPayment());
        query.executeUpdate();
    }

    @Override
    public PaymentHistory findByID(String idPayment) {
        return (PaymentHistory) getSession()
                .createQuery("from com.agit.brooks2.domain.paymenthistory.PaymentHistory where idPayment = :ipj")
                .setParameter("ipj", idPayment)
                .uniqueResult();
    }

    @Override
    public List<PaymentHistory> findAll() {
        Criteria criteria = getSession().createCriteria(PaymentHistory.class);
        return (List<PaymentHistory>) criteria.list();
    }

    @Override
    public List<PaymentHistory> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(PaymentHistory.class);
        if (StringUtil.hasValue(map.get("idPayment"))) {
            criteria.add(Restrictions.eq("idPayment", map.get("idPayment")));
        }
        if (StringUtil.hasValue(map.get("totalPayment"))) {
            criteria.add(Restrictions.like("totalPayment", map.get("totalPayment")).ignoreCase());
        }

        if (StringUtil.hasValue(map.get("statusPayment"))) {
            criteria.add(Restrictions.eq("statusPayment", map.get("statusPayment")));
        }
        return (List<PaymentHistory>) criteria.list();
    }

}
