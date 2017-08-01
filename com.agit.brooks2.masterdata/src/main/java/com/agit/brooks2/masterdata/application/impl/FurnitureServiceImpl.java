/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.application.impl;

import com.agit.brooks2.common.application.FurnitureService;
import com.agit.brooks2.common.dto.masterdata.FurnitureDTO;
import com.agit.brooks2.masterdata.domain.furniture.Furniture;
import com.agit.brooks2.masterdata.domain.furniture.FurnitureBuilder;
import com.agit.brooks2.masterdata.domain.furniture.FurnitureRepository;
import com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler.FurnitureDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;

/**
 *
 * @author 3AD
 */
public class FurnitureServiceImpl implements FurnitureService {

    private FurnitureRepository furnitureRepository;
    private FurnitureDTOAssembler furnitureDTOAssembler;

    public void setFurnitureRepository(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public void setFurnitureDTOAssembler(FurnitureDTOAssembler furnitureDTOAssembler) {
        this.furnitureDTOAssembler = furnitureDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(FurnitureDTO furniture) {
        Furniture p = furnitureRepository.findByID(furniture.getIdFurniture());

        if (p == null) {
            p = furnitureDTOAssembler.toDomain(furniture);
        } else {
            Furniture newInformations = furnitureDTOAssembler.toDomain(furniture);
            p.assignNewFurniture(newInformations);
        }
        furnitureRepository.saveOrUpdate(p);
    }

    @Override
    public void deleteData(FurnitureDTO furniture) {
        Furniture p = furnitureDTOAssembler.toDomain(furniture);
        furnitureRepository.delete(p);
    }

    @Override
    public FurnitureDTO getDummyData() {
        Furniture furniture = new FurnitureBuilder()
                .setIdFurniture("F01")
                .setNameFurniture("NameFurniture")
                .setPhotoFurniture(Byte.MIN_VALUE)
                .setUrlPhoto("URL")
                .setDescriptionFurniture("DescriptionFurniture")
                .setStatus(Status.ACTIVE)
                .setCreatedBy("System")
                .setCreatedDate(new Date())
                .setModifiedBy("System")
                .setModifiedDate(new Date())
                .createFurniture();
        return furnitureDTOAssembler.toDTO(furniture);
    }

    @Override
    public FurnitureDTO findByID(String idFurniture) {
        Validate.notNull(furnitureRepository);
        Furniture p = (Furniture) furnitureRepository.findByID(idFurniture);
        if (p != null) {
            return furnitureDTOAssembler.toDTO(p);
        }
        return null;
    }

    @Override
    public List<FurnitureDTO> findAll() {
        Validate.notNull(furnitureRepository);
        return furnitureDTOAssembler.toDTOs(furnitureRepository.findAll());
    }

    @Override
    public List<FurnitureDTO> findByParams(Map map) {
        Validate.notNull(furnitureRepository);
        List<Furniture> listFurniture = furnitureRepository.findByParams(map);
        if (listFurniture != null) {
            return (List<FurnitureDTO>) furnitureDTOAssembler.toDTOs(listFurniture);
        }
        return null;
    }

}
