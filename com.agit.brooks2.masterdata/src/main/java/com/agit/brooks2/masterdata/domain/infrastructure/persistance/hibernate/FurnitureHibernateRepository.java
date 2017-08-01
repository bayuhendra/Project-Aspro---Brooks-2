package com.agit.brooks2.masterdata.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.common.infrastructure.persistence.hibernate.HibernateRepository;
import com.agit.brooks2.masterdata.domain.furniture.Furniture;
import com.agit.brooks2.masterdata.domain.furniture.FurnitureRepository;
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
public class FurnitureHibernateRepository extends HibernateRepository implements FurnitureRepository {

    @Override
    public void saveOrUpdate(Furniture furniture) {
        getSession().saveOrUpdate(furniture);
    }

    @Override
    public void delete(Furniture furniture) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.masterdata.domain.furniture.Furniture where idFurniture = :ipj");
        query.setParameter("ipj", furniture.getIdFurniture());
        query.executeUpdate();
    }

    @Override
    public Furniture findByID(String idFurniture) {
        return (Furniture) getSession()
                .createQuery("from com.agit.brooks2.masterdata.domain.furniture.Furniture where idFurniture = :ipj")
                .setParameter("ipj", idFurniture)
                .uniqueResult();
    }

    @Override
    public List<Furniture> findAll() {
        Criteria criteria = getSession().createCriteria(Furniture.class);
        return (List<Furniture>) criteria.list();
    }

    @Override
    public List<Furniture> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(Furniture.class);
        if (StringUtil.hasValue(map.get("idFurniture"))) {
            criteria.add(Restrictions.eq("idFurniture", map.get("idFurniture")));
        }
        if (StringUtil.hasValue(map.get("nameFurniture"))) {
            criteria.add(Restrictions.like("nameFurniture", map.get("nameFurniture")).ignoreCase());
        }
        if (StringUtil.hasValue(map.get("status"))) {
            criteria.add(Restrictions.eq("status", map.get("status")));
        }
        return (List<Furniture>) criteria.list();
    }

}
