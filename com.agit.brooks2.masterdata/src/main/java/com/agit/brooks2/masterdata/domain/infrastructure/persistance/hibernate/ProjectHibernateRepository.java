package com.agit.brooks2.masterdata.domain.infrastructure.persistance.hibernate;

import com.agit.brooks2.common.infrastructure.persistence.hibernate.HibernateRepository;
import com.agit.brooks2.masterdata.domain.project.Project;
import com.agit.brooks2.masterdata.domain.project.ProjectRepository;
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
public class ProjectHibernateRepository extends HibernateRepository implements ProjectRepository {

    @Override
    public void saveOrUpdate(Project project) {
        getSession().saveOrUpdate(project);
    }

    @Override
    public void delete(Project project) {
        Query query = getSession().createQuery("delete com.agit.brooks2.masterdata.domain.project.Project where idProject = :ipj");
        query.setParameter("ipj", project.getIdProject());
        query.executeUpdate();
    }

    @Override
    public Project findByID(String idProject) {
        return (Project) getSession()
                .createQuery("from com.agit.brooks2.masterdata.domain.project.Project where idProject = :ipj")
                .setParameter("ipj", idProject)
                .uniqueResult();

    }

    @Override
    public List<Project> findAll() {
        Criteria criteria = getSession().createCriteria(Project.class);
        return (List<Project>) criteria.list();
    }

    @Override
    public List<Project> findByParams(Map map) {
        Criteria criteria = getSession().createCriteria(Project.class);
        if (StringUtil.hasValue(map.get("idProject"))) {
            criteria.add(Restrictions.eq("idProject", map.get("idProject")));
        }
        if (StringUtil.hasValue(map.get("nameProject"))) {
            criteria.add(Restrictions.like("nameProject", map.get("nameProject")).ignoreCase());
        }

        if (StringUtil.hasValue(map.get("status"))) {
            criteria.add(Restrictions.eq("status", map.get("status")));
        }

        return (List<Project>) criteria.list();
    }

}
