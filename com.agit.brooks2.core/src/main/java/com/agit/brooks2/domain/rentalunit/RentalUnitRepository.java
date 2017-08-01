package com.agit.brooks2.domain.rentalunit;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Zaky
 */
public interface RentalUnitRepository {
    
    void saveOrUpdate(RentalUnit rentalUnit);

    void deleteData(RentalUnit rentalUnit);

    RentalUnit findByID(String idRentalUnit);

    List<RentalUnit> findAll();

    List<RentalUnit> findByParams(Map map);
}
