package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.core.RentalUnitDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Zaky
 */
public interface RentalUnitService {
    
    void SaveOrUpdate(RentalUnitDTO rentalUnit);

    void deleteData(RentalUnitDTO rentalUnit);

    RentalUnitDTO getDummyData();

    RentalUnitDTO findByID(String idRentalUnit);

    List<RentalUnitDTO> findAll();

    List<RentalUnitDTO> findByParams(Map map);
}
