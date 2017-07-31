package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.masterdata.ProjectDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface ProjectService {

    void SaveOrUpdate(ProjectDTO project);

    void deleteData(ProjectDTO project);

    ProjectDTO getDummyData();

    ProjectDTO findByID(String idProject);

    List<ProjectDTO> findAll();

    List<ProjectDTO> findByParams(Map map);
}
