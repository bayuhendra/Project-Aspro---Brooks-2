package com.agit.brooks2.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.core.HandOverDTO;
import com.agit.brooks2.common.dto.core.HandOverDTOBuilder;
import com.agit.brooks2.domain.handover.HandOver;
import com.agit.brooks2.domain.handover.HandOverBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zaky
 */
public class HandOverDTOAssembler implements IObjectAssembler<HandOver, HandOverDTO>{

    @Override
    public HandOverDTO toDTO(HandOver domainObject) {
        return new HandOverDTOBuilder()
                .setIdHandOver(domainObject.getIdHandOver())
                .setStartTime(domainObject.getStartTime())
                .setEndTime(domainObject.getEndTime())
                .setStartDate(domainObject.getStartDate())
                .setEndDate(domainObject.getEndDate())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .setStatus(domainObject.getStatus())
                .createHandOverDTO();
    }

    @Override
    public HandOver toDomain(HandOverDTO dtoObject) {
        return new HandOverBuilder()
                .setIdHandOver(dtoObject.getIdHandOver())
                .setStartTime(dtoObject.getStartTime())
                .setEndTime(dtoObject.getEndTime())
                .setStartDate(dtoObject.getStartDate())
                .setEndDate(dtoObject.getEndDate())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .setStatus(dtoObject.getStatus())
                .createHandOver();
    }
    
    public List<HandOver> toDomains(List<HandOverDTO> arg0) {
        List<HandOver> res = new ArrayList<>();
        for (HandOverDTO t : arg0) {
            res.add(this.toDomain(t));
        }
        return res;
    }

    public List<HandOverDTO> toDTOs(List<HandOver> arg0) {
        List<HandOverDTO> res = new ArrayList<>();
        for (HandOver t : arg0) {
            res.add(this.toDTO(t));
        }
        return res;
    }
}
