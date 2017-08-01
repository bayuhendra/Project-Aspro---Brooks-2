package com.agit.brooks2.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.core.SecondaryUnitDTO;
import com.agit.brooks2.common.dto.core.SecondaryUnitDTOBuilder;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnit;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnitBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaky
 */
public class SecondaryUnitDTOAssembler implements IObjectAssembler<SecondaryUnit, SecondaryUnitDTO>{

    @Override
    public SecondaryUnitDTO toDTO(SecondaryUnit domainObject) {
        return new SecondaryUnitDTOBuilder()
                .setIdSecondaryUnit(domainObject.getIdSecondaryUnit())
                .setNameSecondaryUnit(domainObject.getNameSecondaryUnit())
                .setDetailSecondaryUnit(domainObject.getDetailSecondaryUnit())
                .setDataPhotoSecondaryUnit(domainObject.getDataPhotoSecondaryUnit())
                .setUrlPhotoSecondaryUnit(domainObject.getUrlPhotoSecondaryUnit())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .setStatus(domainObject.getStatus())
                .createSecondaryUnitDTO();
    }

    @Override
    public SecondaryUnit toDomain(SecondaryUnitDTO dtoObject) {
        return new SecondaryUnitBuilder()
                .setIdSecondaryUnit(dtoObject.getIdSecondaryUnit())
                .setNameSecondaryUnit(dtoObject.getNameSecondaryUnit())
                .setDetailSecondaryUnit(dtoObject.getDetailSecondaryUnit())
                .setDataPhotoSecondaryUnit(dtoObject.getDataPhotoSecondaryUnit())
                .setUrlPhotoSecondaryUnit(dtoObject.getUrlPhotoSecondaryUnit())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .setStatus(dtoObject.getStatus())
                .createSecondaryUnit();                        
    }
    
    public List<SecondaryUnit> toDomains(List<SecondaryUnitDTO> arg0) {
        List<SecondaryUnit> res = new ArrayList<>();
        for (SecondaryUnitDTO t : arg0) {
            res.add(this.toDomain(t));
        }
        return res;
    }

    public List<SecondaryUnitDTO> toDTOs(List<SecondaryUnit> arg0) {
        List<SecondaryUnitDTO> res = new ArrayList<>();
        for (SecondaryUnit t : arg0) {
            res.add(this.toDTO(t));
        }
        return res;
    }
    
}
