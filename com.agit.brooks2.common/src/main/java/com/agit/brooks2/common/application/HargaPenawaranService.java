package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface HargaPenawaranService {

    void saveOrUpdate(HargaPenawaranDTO hargaPenawaran);

    void delete(HargaPenawaranDTO hargaPenawaran);

    HargaPenawaranDTO findByID(String hargaPenawaranID);

    List<HargaPenawaranDTO> findAll();

    List<HargaPenawaranDTO> findByParams(Map map);

    HargaPenawaranDTO getDummy();
}
