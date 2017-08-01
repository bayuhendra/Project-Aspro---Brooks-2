package com.agit.brooks2.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.core.RentalUnitDTO;
import com.agit.brooks2.common.dto.core.RentalUnitDTOBuilder;
import com.agit.brooks2.domain.rentalunit.RentalUnit;
import com.agit.brooks2.domain.rentalunit.RentalUnitBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaky
 */
public class RentalUnitDTOAssembler implements IObjectAssembler<RentalUnit, RentalUnitDTO>{

    @Override
    public RentalUnitDTO toDTO(RentalUnit domainObject) {
        return new RentalUnitDTOBuilder()
                .setIdRentalUnit(domainObject.getIdRentalUnit())
                .setNameRentalUnit(domainObject.getNameRentalUnit())
                .setDetailRentalUnit(domainObject.getDetailRentalUnit())
                .setDataPhotoRentalUnit(domainObject.getDataPhotoRentalUnit())
                .setUrlPhotoRentalUnit(domainObject.getUrlPhotoRentalUnit())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .setStatus(domainObject.getStatus())
                .createRentalUnitDTO();
    }

    @Override
    public RentalUnit toDomain(RentalUnitDTO dtoObject) {
        return new RentalUnitBuilder()
                .setIdRentalUnit(dtoObject.getIdRentalUnit())
                .setNameRentalUnit(dtoObject.getNameRentalUnit())
                .setDetailRentalUnit(dtoObject.getDetailRentalUnit())
                .setDataPhotoRentalUnit(dtoObject.getDataPhotoRentalUnit())
                .setUrlPhotoRentalUnit(dtoObject.getUrlPhotoRentalUnit())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .setStatus(dtoObject.getStatus())
                .createRentalUnit();                
    }
    
    public List<RentalUnit> toDomains(List<RentalUnitDTO> arg0) {
        List<RentalUnit> res = new ArrayList<>();
        for (RentalUnitDTO t : arg0) {
            res.add(this.toDomain(t));
        }
        return res;
    }

    public List<RentalUnitDTO> toDTOs(List<RentalUnit> arg0) {
        List<RentalUnitDTO> res = new ArrayList<>();
        for (RentalUnit t : arg0) {
            res.add(this.toDTO(t));
        }
        return res;
    }
}
