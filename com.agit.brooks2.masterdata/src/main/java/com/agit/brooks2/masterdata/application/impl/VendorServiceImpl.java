package com.agit.brooks2.masterdata.application.impl;

import com.agit.brooks2.common.application.VendorService;
import com.agit.brooks2.common.dto.masterdata.VendorDTO;
import com.agit.brooks2.masterdata.domain.vendor.Vendor;
import com.agit.brooks2.masterdata.domain.vendor.VendorBuilder;
import com.agit.brooks2.masterdata.domain.vendor.VendorRepository;
import com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler.VendorDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;

/**
 *
 * @author 3AD
 */
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorDTOAssembler vendorDTOAssembler;

    public void setVendorRepository(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public void setVendorDTOAssembler(VendorDTOAssembler vendorDTOAssembler) {
        this.vendorDTOAssembler = vendorDTOAssembler;
    }

    @Override
    public void saveOrUpdate(VendorDTO vendor) {
        Vendor p = vendorRepository.findByID(vendor.getVendorID());

        if (p == null) {
            p = vendorDTOAssembler.toDomain(vendor);
        } else {
            Vendor newVendor = vendorDTOAssembler.toDomain(vendor);
            p.assignNewVendor(newVendor);
        }
        vendorRepository.saveOrUpdate(p);
    }

    @Override
    public void delete(VendorDTO vendor) {
        Vendor p = vendorDTOAssembler.toDomain(vendor);
        vendorRepository.delete(p);
    }

    @Override
    public VendorDTO findByID(String vendorID) {
        Validate.notNull(vendorRepository);
        Vendor p = (Vendor) vendorRepository.findByID(vendorID);
        if (p != null) {
            return vendorDTOAssembler.toDTO(p);
        }
        return null;
    }

    @Override
    public List<VendorDTO> findAll() {
        Validate.notNull(vendorRepository);
        return vendorDTOAssembler.toDTOs(vendorRepository.findAll());
    }

    @Override
    public List<VendorDTO> findByParams(Map map) {
        Validate.notNull(vendorRepository);
        List<Vendor> listVendor = vendorRepository.findByParams(map);
        if (listVendor != null) {
            return (List<VendorDTO>) vendorDTOAssembler.toDTOs(listVendor);
        }
        return null;
    }

    @Override
    public VendorDTO getDummy() {
        Vendor vendor = new VendorBuilder()
                .setVendorID("A01")
                .setNamaVendor("AAA")
                .setStatusVendor(Status.ACTIVE)
                .setCreatedBy(null)
                .setCreatedDate(null)
                .setModifiedBy(null)
                .setModifiedDate(null)
                .createVendor();
        return vendorDTOAssembler.toDTO(vendor);
    }

}
