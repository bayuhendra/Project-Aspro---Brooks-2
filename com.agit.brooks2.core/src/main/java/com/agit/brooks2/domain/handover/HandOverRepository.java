package com.agit.brooks2.domain.handover;

import java.util.List;
import java.util.Map;
/**
 *
 * @author Zaky
 */
public interface HandOverRepository {
    void saveOrUpdate(HandOver handOver);

    void deleteData(HandOver handOver);

    HandOver findByID(String idHandOver);

    List<HandOver> findAll();

    List<HandOver> findByParams(Map map);
}
