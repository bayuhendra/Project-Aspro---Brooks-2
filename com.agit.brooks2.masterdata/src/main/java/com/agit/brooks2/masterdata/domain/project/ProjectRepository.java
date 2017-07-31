package com.agit.brooks2.masterdata.domain.project;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface ProjectRepository {

    void saveOrUpdate(Project project);

    void delete(Project project);

    Project findByID(String idProject);

    List<Project> findAll();

    List<Project> findByParams(Map map);

}
