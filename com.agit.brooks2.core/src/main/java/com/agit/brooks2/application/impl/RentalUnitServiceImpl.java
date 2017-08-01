package com.agit.brooks2.application.impl;

import com.agit.brooks2.common.application.RentalUnitService;
import com.agit.brooks2.common.dto.core.RentalUnitDTO;
import com.agit.brooks2.domain.rentalunit.RentalUnit;
import com.agit.brooks2.domain.rentalunit.RentalUnitBuilder;
import com.agit.brooks2.domain.rentalunit.RentalUnitRepository;
import com.agit.brooks2.interfaces.web.facade.dto.assembler.RentalUnitDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;

/**
 *
 * @author Zaky
 */
public class RentalUnitServiceImpl implements RentalUnitService{
    private RentalUnitRepository rentalUnitRepository;
    private RentalUnitDTOAssembler rentalUnitDTOAssembler;

    public void setRentalUnitRepository(RentalUnitRepository rentalUnitRepository) {
        this.rentalUnitRepository = rentalUnitRepository;
    }

    public void setRentalUnitDTOAssembler(RentalUnitDTOAssembler rentalUnitDTOAssembler) {
        this.rentalUnitDTOAssembler = rentalUnitDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(RentalUnitDTO rentalUnit) {
        RentalUnit t = rentalUnitRepository.findByID(rentalUnit.getIdRentalUnit());

        if (t == null) {
            t = rentalUnitDTOAssembler.toDomain(rentalUnit);
        } else {
            RentalUnit newRentalUnit = rentalUnitDTOAssembler.toDomain(rentalUnit);
            t.assignNewRentalUnit(newRentalUnit);

        }
        rentalUnitRepository.saveOrUpdate(t);
    }

    @Override
    public void deleteData(RentalUnitDTO rentalUnit) {
        RentalUnit p = rentalUnitDTOAssembler.toDomain(rentalUnit);
        rentalUnitRepository.deleteData(p);
    }

    @Override
    public RentalUnitDTO getDummyData() {
        RentalUnit rentalUnit1 = new RentalUnitBuilder()
                .setIdRentalUnit("RU0001")
                .setNameRentalUnit("Rumah1")
                .setDetailRentalUnit("Rumah nomor 1")
                .setDataPhotoRentalUnit("gambar Rumah 1")
                .setUrlPhotoRentalUnit("url rumah 1")
                .setCreatedBy("DUMMY")
                .setCreatedDate(new Date())
                .setModifiedBy("DUMMY")
                .setModifiedDate(new Date())
                .setStatus(Status.ACTIVE)
                .createRentalUnit();
        return rentalUnitDTOAssembler.toDTO(rentalUnit1);
    }

    @Override
    public RentalUnitDTO findByID(String idRentalUnit) {
        Validate.notNull(rentalUnitRepository);
        RentalUnit b = (RentalUnit) rentalUnitRepository.findByID(idRentalUnit);
        if (b != null) {
            return rentalUnitDTOAssembler.toDTO(b);
        }
        return null;
    }

    @Override
    public List<RentalUnitDTO> findAll() {
        Validate.notNull(rentalUnitRepository);
        return rentalUnitDTOAssembler.toDTOs(rentalUnitRepository.findAll());
    }

    @Override
    public List<RentalUnitDTO> findByParams(Map map) {
        Validate.notNull(rentalUnitRepository);
        List<RentalUnit> listRentalUnit = rentalUnitRepository.findByParams(map);
        if (listRentalUnit != null) {
            return (List<RentalUnitDTO>) rentalUnitDTOAssembler.toDTOs(listRentalUnit);
        }
        return null;
    }
    
}
