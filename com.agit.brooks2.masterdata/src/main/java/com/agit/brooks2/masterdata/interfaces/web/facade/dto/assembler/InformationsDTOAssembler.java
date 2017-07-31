package com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.masterdata.InformationsDTO;
import com.agit.brooks2.common.dto.masterdata.InformationsDTOBuilder;
import com.agit.brooks2.masterdata.domain.information.Informations;
import com.agit.brooks2.masterdata.domain.information.InformationsBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3AD
 */
public class InformationsDTOAssembler implements IObjectAssembler<Informations, InformationsDTO> {

    @Override
    public InformationsDTO toDTO(Informations domainObject) {
        return new InformationsDTOBuilder()
                .setIdNews(domainObject.getIdNews())
                .setTittleNews(domainObject.getTittleNews())
                .setDescriptionNews(domainObject.getDescriptionNews())
                .setStatusProject(domainObject.getStatusProject())
                .setPhotoInformation(domainObject.getPhotoInformation())
                .setUrlPhotoInformation(domainObject.getUrlPhotoInformation())
                .setStatus(domainObject.getStatus())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .createInformationsDTO();
    }

    @Override
    public Informations toDomain(InformationsDTO dtoObject) {
        return new InformationsBuilder()
                .setIdNews(dtoObject.getIdNews())
                .setTittleNews(dtoObject.getTittleNews())
                .setDescriptionNews(dtoObject.getDescriptionNews())
                .setStatusProject(dtoObject.getStatusProject())
                .setPhotoInformation(dtoObject.getPhotoInformation())
                .setUrlPhotoInformation(dtoObject.getUrlPhotoInformation())
                .setStatus(dtoObject.getStatus())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .createInformations();
    }

    public List<Informations> toDomains(List<InformationsDTO> arg0) {
        List<Informations> res = new ArrayList<>();
        for (InformationsDTO p : arg0) {
            res.add(this.toDomain(p));
        }
        return res;
    }

    public List<InformationsDTO> toDTOs(List<Informations> arg0) {
        List<InformationsDTO> res = new ArrayList<>();
        for (Informations p : arg0) {
            res.add(this.toDTO(p));
        }
        return res;
    }
}
