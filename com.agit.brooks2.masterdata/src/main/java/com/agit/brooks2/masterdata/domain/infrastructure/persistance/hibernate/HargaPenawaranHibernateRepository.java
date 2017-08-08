package com.agit.brooks2.masterdata.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.common.infrastructure.persistence.hibernate.HibernateRepository;
import com.agit.brooks2.masterdata.domain.HargaPenawaran;
import com.agit.brooks2.masterdata.domain.HargaPenawaranRepository;
import com.agit.brooks2.util.JDCStringUtil;
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
public class HargaPenawaranHibernateRepository extends HibernateRepository implements HargaPenawaranRepository {

    @Override
    public void saveOrUpadate(HargaPenawaran hargaPenawaran) {
        getSession().saveOrUpdate(hargaPenawaran);
    }

    @Override
    public void delete(HargaPenawaran hargaPenawaran) {
        Query query = getSession().createQuery("Delete From com.agit.brooks2.masterdata.domain.HargaPenawaran Where hargaPenawaranID = :hid");
        query.setParameter("hid", hargaPenawaran.getHargaPenawaranID());
        query.executeUpdate();
    }

    @Override
    public HargaPenawaran findByID(String hargaPenawaranID) {
        Query query = getSession().createQuery("From com.agit.brooks2.masterdata.domain.HargaPenawaran Where hargaPenawaranID = :hid");
        query.setParameter("hid", hargaPenawaranID);
        return (HargaPenawaran) query.uniqueResult();
    }

    @Override
    public List<HargaPenawaran> findAll() {
        Criteria criteria = getSession().createCriteria(HargaPenawaran.class);
        return (List<HargaPenawaran>) criteria.list();
    }

    @Override
    public List<HargaPenawaran> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(HargaPenawaran.class);
        if (JDCStringUtil.hasValue(map.get("hargaPenawaranID"))) {
            criteria.add(Restrictions.eq("hargaPenawaranID", map.get("hargaPenawaranID")));
        }
        if (JDCStringUtil.hasValue(map.get("harga"))) {
            criteria.add(Restrictions.like("harga", "%" + map.get("harga") + "%").ignoreCase());
        }
        if (JDCStringUtil.hasValue(map.get("status"))) {
            criteria.add(Restrictions.eq("status", map.get("status")));
        }
        return (List<HargaPenawaran>) criteria.list();
    }

}
