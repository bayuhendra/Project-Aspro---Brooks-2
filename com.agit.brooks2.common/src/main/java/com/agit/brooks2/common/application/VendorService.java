package com.agit.brooks2.common.application;

import com.agit.brooks2.common.dto.masterdata.VendorDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface VendorService {

    void saveOrUpdate(VendorDTO vendor);

    void delete(VendorDTO vendor);

    VendorDTO findByID(String vendorID);

    List<VendorDTO> findAll();

    List<VendorDTO> findByParams(Map map);

    VendorDTO getDummy();
}
