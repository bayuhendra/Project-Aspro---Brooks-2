package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.masterdata.InformationsDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface InformationsService {

    void SaveOrUpdate(InformationsDTO informations);

    void deleteData(InformationsDTO informations);

    InformationsDTO getDummyData();

    InformationsDTO findByID(String idNews);

    List<InformationsDTO> findAll();

    List<InformationsDTO> findByParams(Map map);
}
