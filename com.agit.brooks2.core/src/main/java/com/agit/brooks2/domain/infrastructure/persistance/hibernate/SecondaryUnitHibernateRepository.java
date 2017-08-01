package com.agit.brooks2.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.domain.secondaryunit.SecondaryUnit;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnitRepository;
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
public class SecondaryUnitHibernateRepository extends HibernateRepository implements SecondaryUnitRepository{

    @Override
    public void saveOrUpdate(SecondaryUnit secondaryUnit) {
        getSession().saveOrUpdate(secondaryUnit);
    }

    @Override
    public void deleteData(SecondaryUnit secondaryUnit) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.domain.secondaryunit.SecondaryUnit where idSecondaryUnit = :tid");
        query.setParameter("tid", secondaryUnit.getIdSecondaryUnit());
        query.executeUpdate();
    }

    @Override
    public SecondaryUnit findByID(String idSecondaryUnit) {
        return (SecondaryUnit) getSession()
                .createQuery("from com.agit.brooks2.domain.secondaryunit.SecondaryUnit where idSecondaryUnit = :tid")
                .setParameter("tid", idSecondaryUnit)
                .uniqueResult();
    }

    @Override
    public List<SecondaryUnit> findAll() {
        return (List<SecondaryUnit>) getSession()
                .createQuery("from com.agit.brooks2.domain.secondaryunit.SecondaryUnit ")
                .list();
    }

    @Override
    public List<SecondaryUnit> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(SecondaryUnit.class);
        if (StringUtil.hasValue(map.get("idSecondaryUnit"))) {
            criteria.add(Restrictions.eq("idSecondaryUnit", map.get("idSecondaryUnit")));
        }
        if (StringUtil.hasValue(map.get("namaSecondaryUnit"))) {
            criteria.add(Restrictions.like("namaSecondaryUnit", "%" + map.get("namaSecondaryUnit") + "%").ignoreCase());
        }
        return criteria.list();
    }
    
}
