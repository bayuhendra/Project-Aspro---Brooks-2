package com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.masterdata.VendorDTO;
import com.agit.brooks2.common.dto.masterdata.VendorDTOBuilder;
import com.agit.brooks2.masterdata.domain.vendor.Vendor;
import com.agit.brooks2.masterdata.domain.vendor.VendorBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3AD
 */
public class VendorDTOAssembler implements IObjectAssembler<Vendor, VendorDTO> {

    @Override
    public VendorDTO toDTO(Vendor domainObject) {
        return new VendorDTOBuilder()
                .setVendorID(domainObject.getVendorID())
                .setNamaVendor(domainObject.getNamaVendor())
                .setStatusVendor(domainObject.getStatusVendor())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .createVendorDTO();
    }

    @Override
    public Vendor toDomain(VendorDTO dtoObject) {
        return new VendorBuilder()
                .setVendorID(dtoObject.getVendorID())
                .setNamaVendor(dtoObject.getNamaVendor())
                .setStatusVendor(dtoObject.getStatusVendor())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .createVendor();
    }

    public List<Vendor> toDomains(List<VendorDTO> arg0) {
        List<Vendor> res = new ArrayList<>();
        for (VendorDTO p : arg0) {
            res.add(this.toDomain(p));
        }
        return res;
    }

    public List<VendorDTO> toDTOs(List<Vendor> arg0) {
        List<VendorDTO> res = new ArrayList<>();
        for (Vendor p : arg0) {
            res.add(this.toDTO(p));
        }
        return res;
    }

}
