package com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler;

import com.agit.brooks2.common.dto.masterdata.FurnitureDTO;
import com.agit.brooks2.common.dto.masterdata.FurnitureDTOBuilder;
import com.agit.brooks2.masterdata.domain.furniture.Furniture;
import com.agit.brooks2.masterdata.domain.furniture.FurnitureBuilder;
import com.agit.brooks2.shared.object.IObjectAssembler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3AD
 */
public class FurnitureDTOAssembler implements IObjectAssembler<Furniture, FurnitureDTO> {

    @Override
    public FurnitureDTO toDTO(Furniture domainObject) {
        return new FurnitureDTOBuilder()
                .setIdFurniture(domainObject.getIdFurniture())
                .setNameFurniture(domainObject.getNameFurniture())
                .setPhotoFurniture(domainObject.getPhotoFurniture())
                .setUrlPhoto(domainObject.getUrlPhoto())
                .setDescriptionFurniture(domainObject.getDescriptionFurniture())
                .setStatus(domainObject.getStatus())
                .setCreatedBy(domainObject.getCreatedBy())
                .setCreatedDate(domainObject.getCreatedDate())
                .setModifiedBy(domainObject.getModifiedBy())
                .setModifiedDate(domainObject.getModifiedDate())
                .createFurnitureDTO();
    }

    @Override
    public Furniture toDomain(FurnitureDTO dtoObject) {
        return new FurnitureBuilder()
                .setIdFurniture(dtoObject.getIdFurniture())
                .setNameFurniture(dtoObject.getNameFurniture())
                .setPhotoFurniture(dtoObject.getPhotoFurniture())
                .setUrlPhoto(dtoObject.getUrlPhoto())
                .setDescriptionFurniture(dtoObject.getDescriptionFurniture())
                .setStatus(dtoObject.getStatus())
                .setCreatedBy(dtoObject.getCreatedBy())
                .setCreatedDate(dtoObject.getCreatedDate())
                .setModifiedBy(dtoObject.getModifiedBy())
                .setModifiedDate(dtoObject.getModifiedDate())
                .createFurniture();
    }

    public List<Furniture> toDomains(List<FurnitureDTO> arg0) {
        List<Furniture> res = new ArrayList<>();
        for (FurnitureDTO p : arg0) {
            res.add(this.toDomain(p));
        }
        return res;
    }

    public List<FurnitureDTO> toDTOs(List<Furniture> arg0) {
        List<FurnitureDTO> res = new ArrayList<>();
        for (Furniture p : arg0) {
            res.add(this.toDTO(p));
        }
        return res;
    }

}
