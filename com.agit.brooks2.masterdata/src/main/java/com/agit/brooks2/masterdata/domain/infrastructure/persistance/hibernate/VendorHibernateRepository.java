package com.agit.brooks2.masterdata.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.common.infrastructure.persistence.hibernate.HibernateRepository;
import com.agit.brooks2.masterdata.domain.vendor.Vendor;
import com.agit.brooks2.masterdata.domain.vendor.VendorRepository;
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
public class VendorHibernateRepository extends HibernateRepository implements VendorRepository {

    @Override
    public void saveOrUpdate(Vendor vendor) {
        getSession().saveOrUpdate(vendor);
    }

    @Override
    public void delete(Vendor vendor) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.masterdata.domain.vendor.Vendor where vendorID = :vid");
        query.setParameter("vid", vendor.getVendorID());
        query.executeUpdate();
    }

    @Override
    public Vendor findByID(String vendorID) {
        Criteria criteria = getSession().createCriteria(Vendor.class);
        criteria.add(Restrictions.eq("vendorID", vendorID));
        return (Vendor) criteria.uniqueResult();
    }

    @Override
    public List<Vendor> findAll() {
        Criteria criteria = getSession().createCriteria(Vendor.class);
        return (List<Vendor>) criteria.list();
    }

    @Override
    public List<Vendor> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(Vendor.class);
        if (JDCStringUtil.hasValue(map.get("vendorID"))) {
            criteria.add(Restrictions.eq("vendorID", map.get("vendorID")));
        }
        if (JDCStringUtil.hasValue(map.get("namaVendor"))) {
            criteria.add(Restrictions.like("namaVendor", "%" + map.get("namaVendor") + "%").ignoreCase());
        }
        if (JDCStringUtil.hasValue(map.get("statusVendor"))) {
            criteria.add(Restrictions.eq("statusVendor", map.get("statusVendor")));
        }

        return (List<Vendor>) criteria.list();
    }

}
