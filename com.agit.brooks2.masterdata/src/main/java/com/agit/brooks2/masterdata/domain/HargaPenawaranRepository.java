package com.agit.brooks2.masterdata.domain;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface HargaPenawaranRepository {

    void saveOrUpadate(HargaPenawaran hargaPenawaran);

    void delete(HargaPenawaran hargaPenawaran);

    HargaPenawaran findByID(String hargaPenawaranID);

    List<HargaPenawaran> findAll();

    List<HargaPenawaran> findByParams(Map map);
}
