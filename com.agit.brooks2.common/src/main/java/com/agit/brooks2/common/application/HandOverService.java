package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.core.HandOverDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zaky
 */
public interface HandOverService {
    
    void SaveOrUpdate(HandOverDTO handOver);

    void deleteData(HandOverDTO handOver);

    HandOverDTO getDummyData();

    HandOverDTO findByID(String idHandOver);

    List<HandOverDTO> findAll();

    List<HandOverDTO> findByParams(Map map);
}
