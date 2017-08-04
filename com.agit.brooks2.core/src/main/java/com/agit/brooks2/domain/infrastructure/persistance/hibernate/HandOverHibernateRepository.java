package com.agit.brooks2.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.domain.handover.HandOver;
import com.agit.brooks2.domain.handover.HandOverRepository;
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
 * @author Zaky
 */
@Repository
@Transactional
public class HandOverHibernateRepository extends HibernateRepository implements HandOverRepository {

    @Override
    public void saveOrUpdate(HandOver handOver) {
        getSession().saveOrUpdate(handOver);
    }

    @Override
    public void deleteData(HandOver handOver) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.domain.handover.HandOver where idHandOver = :tid");
        query.setParameter("tid", handOver.getIdHandOver());
        query.executeUpdate();
    }

    @Override
    public HandOver findByID(String idHandOver) {
        return (HandOver) getSession()
                .createQuery("from com.agit.brooks2.domain.handover.HandOver where idHandOver = :tid")
                .setParameter("tid", idHandOver)
                .uniqueResult();
    }

    @Override
    public List<HandOver> findAll() {
        return (List<HandOver>) getSession()
                .createQuery("from com.agit.brooks2.domain.handover.HandOver ")
                .list();
    }

    @Override
    public List<HandOver> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(HandOver.class);
        if (StringUtil.hasValue(map.get("idHandOver"))) {
            criteria.add(Restrictions.eq("idHandOver", map.get("idHandOver")));
        }
        if (StringUtil.hasValue(map.get("startDate"))) {
            criteria.add(Restrictions.eq("startDate", map.get("startDate")));
        }
        if (StringUtil.hasValue(map.get("endDate"))) {
            criteria.add(Restrictions.eq("endDate", map.get("endDate")));
        }
        if (StringUtil.hasValue(map.get("status"))) {
            criteria.add(Restrictions.eq("status", map.get("status")));
        }
        return criteria.list();
    }

}
