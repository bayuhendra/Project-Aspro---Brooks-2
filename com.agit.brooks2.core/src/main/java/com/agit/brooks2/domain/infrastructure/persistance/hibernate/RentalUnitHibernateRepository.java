package com.agit.brooks2.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.domain.rentalunit.RentalUnit;
import com.agit.brooks2.domain.rentalunit.RentalUnitRepository;
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
public class RentalUnitHibernateRepository extends HibernateRepository implements RentalUnitRepository {

    @Override
    public void saveOrUpdate(RentalUnit rentalUnit) {
        getSession().saveOrUpdate(rentalUnit);
    }

    @Override
    public void deleteData(RentalUnit rentalUnit) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.domain.rentalunit.RentalUnit where idRentalUnit = :tid");
        query.setParameter("tid", rentalUnit.getIdRentalUnit());
        query.executeUpdate();
    }

    @Override
    public RentalUnit findByID(String idRentalUnit) {
        return (RentalUnit) getSession()
                .createQuery("from com.agit.brooks2.domain.rentalunit.RentalUnit where idRentalUnit = :tid")
                .setParameter("tid", idRentalUnit)
                .uniqueResult();
    }

    @Override
    public List<RentalUnit> findAll() {
        return (List<RentalUnit>) getSession()
                .createQuery("from com.agit.brooks2.domain.rentalunit.RentalUnit ")
                .list();
    }

    @Override
    public List<RentalUnit> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(RentalUnit.class);
        if (StringUtil.hasValue(map.get("idRentalUnit"))) {
            criteria.add(Restrictions.eq("idRentalUnit", map.get("idRentalUnit")));
        }
        if (StringUtil.hasValue(map.get("namaRentalUnit"))) {
            criteria.add(Restrictions.like("namaRentalUnit", "%" + map.get("namaRentalUnit") + "%").ignoreCase());
        }
        return criteria.list();
    }
    
}
