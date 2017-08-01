package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.core.SecondaryUnitDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zaky
 */
public interface SecondaryUnitService {
    
    void SaveOrUpdate(SecondaryUnitDTO secondaryUnit);

    void deleteData(SecondaryUnitDTO secondaryUnit);

    SecondaryUnitDTO getDummyData();

    SecondaryUnitDTO findByID(String idSecondaryUnit);

    List<SecondaryUnitDTO> findAll();

    List<SecondaryUnitDTO> findByParams(Map map);
}
