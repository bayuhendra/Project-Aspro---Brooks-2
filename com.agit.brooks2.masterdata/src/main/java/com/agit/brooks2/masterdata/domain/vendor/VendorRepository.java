package com.agit.brooks2.masterdata.domain.vendor;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 3AD
 */
public interface VendorRepository {

    void saveOrUpdate(Vendor vendor);

    void delete(Vendor vendor);

    Vendor findByID(String vendorID);

    List<Vendor> findAll();

    List<Vendor> findByParams(Map map);

}
