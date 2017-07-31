package com.agit.brooks2.masterdata.domain.information;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface InformationsRepository {

    void saveOrUpdate(Informations informations);

    void delete(Informations informations);

    Informations findByID(String idNews);

    List<Informations> findAll();

    List<Informations> findByParams(Map map);
}
