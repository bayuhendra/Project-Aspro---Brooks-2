package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.masterdata.FurnitureDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface FurnitureService {

    void SaveOrUpdate(FurnitureDTO furniture);

    void deleteData(FurnitureDTO furniture);

    FurnitureDTO getDummyData();

    FurnitureDTO findByID(String idFurniture);

    List<FurnitureDTO> findAll();

    List<FurnitureDTO> findByParams(Map map);
}
