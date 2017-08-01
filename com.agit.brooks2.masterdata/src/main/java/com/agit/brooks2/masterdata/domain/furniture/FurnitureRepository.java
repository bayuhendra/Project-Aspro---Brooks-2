package com.agit.brooks2.masterdata.domain.furniture;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface FurnitureRepository {

    void saveOrUpdate(Furniture furniture);

    void delete(Furniture furniture);

    Furniture findByID(String idFurniture);

    List<Furniture> findAll();

    List<Furniture> findByParams(Map map);
}
