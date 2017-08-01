package com.agit.brooks2.masterdata.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.common.infrastructure.persistence.hibernate.HibernateRepository;
import com.agit.brooks2.masterdata.domain.information.Informations;
import com.agit.brooks2.masterdata.domain.information.InformationsRepository;
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
public class InformationsHibernateRepository extends HibernateRepository implements InformationsRepository {

    @Override
    public void saveOrUpdate(Informations informations) {
        getSession().saveOrUpdate(informations);
    }

    @Override
    public void delete(Informations informations) {
        Query query = getSession().createQuery("delete from com.agit.brooks2.masterdata.domain.information.Informations where idNews = :ipj");
        query.setParameter("ipj", informations.getIdNews());
        query.executeUpdate();
    }

    @Override
    public Informations findByID(String idNews) {
        return (Informations) getSession()
                .createQuery("from com.agit.brooks2.masterdata.domain.information.Informations where idNews = :ipj")
                .setParameter("ipj", idNews)
                .uniqueResult();
    }

    @Override
    public List<Informations> findAll() {
        Criteria criteria = getSession().createCriteria(Informations.class);
        return (List<Informations>) criteria.list();
    }

    @Override
    public List<Informations> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(Informations.class);
        if (StringUtil.hasValue(map.get("idNews"))) {
            criteria.add(Restrictions.eq("idNews", map.get("idNews")));
        }
        if (StringUtil.hasValue(map.get("tittleNews"))) {
            criteria.add(Restrictions.like("tittleNews", map.get("tittleNews")).ignoreCase());
        }
        if (StringUtil.hasValue(map.get("statusProject"))) {
            criteria.add(Restrictions.eq("statusProject", map.get("statusProject")));
        }
        if (StringUtil.hasValue(map.get("status"))) {
            criteria.add(Restrictions.eq("status", map.get("status")));
        }
        return (List<Informations>) criteria.list();
    }

}
