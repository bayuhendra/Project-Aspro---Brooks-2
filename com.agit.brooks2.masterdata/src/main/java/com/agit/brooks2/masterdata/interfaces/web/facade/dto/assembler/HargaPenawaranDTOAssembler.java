package com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTO;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTOBuilder;
import com.agit.brooks2.masterdata.domain.HargaPenawaran;
import com.agit.brooks2.masterdata.domain.HargaPenawaranBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3AD
 */
public class HargaPenawaranDTOAssembler implements IObjectAssembler<HargaPenawaran, HargaPenawaranDTO> {

    @Override
    public HargaPenawaranDTO toDTO(HargaPenawaran domainObject) {
        return new HargaPenawaranDTOBuilder()
                .setHargaPenawaranID(domainObject.getHargaPenawaranID())
                .setHarga(domainObject.getHarga())
                .setStatus(domainObject.getStatus())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .createHargaPenawaranDTO();
    }

    @Override
    public HargaPenawaran toDomain(HargaPenawaranDTO dtoObject) {
        return new HargaPenawaranBuilder()
                .setHargaPenawaranID(dtoObject.getHargaPenawaranID())
                .setHarga(dtoObject.getHarga())
                .setStatus(dtoObject.getStatus())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .createHargaPenawaran();
    }

    public List<HargaPenawaran> toDomains(List<HargaPenawaranDTO> arg0) {
        List<HargaPenawaran> res = new ArrayList<>();
        for (HargaPenawaranDTO p : arg0) {
            res.add(this.toDomain(p));
        }
        return res;
    }

    public List<HargaPenawaranDTO> toDTOs(List<HargaPenawaran> arg0) {
        List<HargaPenawaranDTO> res = new ArrayList<>();
        for (HargaPenawaran p : arg0) {
            res.add(this.toDTO(p));
        }
        return res;
    }

}
