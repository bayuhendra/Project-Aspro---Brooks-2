package com.agit.brooks2.domain.secondaryunit;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Zaky
 */
public interface SecondaryUnitRepository {
    void saveOrUpdate(SecondaryUnit secondaryUnit);

    void deleteData(SecondaryUnit secondaryUnit);

    SecondaryUnit findByID(String idSecondaryUnit);

    List<SecondaryUnit> findAll();

    List<SecondaryUnit> findByParams(Map map);
}
