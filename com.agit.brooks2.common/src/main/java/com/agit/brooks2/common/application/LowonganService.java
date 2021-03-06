package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.crm.LowonganDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bayu Hendra Setiawan
 */
public interface LowonganService {

    void SaveOrUpdate(LowonganDTO lowongan);

    void deleteData(LowonganDTO lowongan);

    LowonganDTO getDummyData();

    LowonganDTO findByID(String idLowongan);

    List<LowonganDTO> findAll();

    List<LowonganDTO> findByParams(Map map);

}
