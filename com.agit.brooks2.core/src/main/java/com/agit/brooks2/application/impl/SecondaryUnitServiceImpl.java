package com.agit.brooks2.application.impl;

import com.agit.brooks2.common.application.SecondaryUnitService;
import com.agit.brooks2.common.dto.core.SecondaryUnitDTO;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnit;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnitBuilder;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnitRepository;
import com.agit.brooks2.interfaces.web.facade.dto.assembler.SecondaryUnitDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Zaky
 */
@Controller
public class SecondaryUnitServiceImpl implements SecondaryUnitService {

    private SecondaryUnitRepository secondaryUnitRepository;
    private SecondaryUnitDTOAssembler secondaryUnitDTOAssembler;

    public void setSecondaryUnitRepository(SecondaryUnitRepository secondaryUnitRepository) {
        this.secondaryUnitRepository = secondaryUnitRepository;
    }

    public void setSecondaryUnitDTOAssembler(SecondaryUnitDTOAssembler secondaryUnitDTOAssembler) {
        this.secondaryUnitDTOAssembler = secondaryUnitDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(SecondaryUnitDTO secondaryUnit) {
        SecondaryUnit t = secondaryUnitRepository.findByID(secondaryUnit.getIdSecondaryUnit());

        if (t == null) {
            t = secondaryUnitDTOAssembler.toDomain(secondaryUnit);
        } else {
            SecondaryUnit newSecondaryUnit = secondaryUnitDTOAssembler.toDomain(secondaryUnit);
            t.assignNewSecondaryUnit(newSecondaryUnit);

        }
        secondaryUnitRepository.saveOrUpdate(t);
    }

    @Override
    public void deleteData(SecondaryUnitDTO secondaryUnit) {
        SecondaryUnit p = secondaryUnitDTOAssembler.toDomain(secondaryUnit);
        secondaryUnitRepository.deleteData(p);
    }

    @Override
    public SecondaryUnitDTO getDummyData() {
        SecondaryUnit secondaryUnit1 = new SecondaryUnitBuilder()
                .setIdSecondaryUnit("SU0001")
                .setNameSecondaryUnit("Rumah1")
                .setDetailSecondaryUnit("Rumah nomor 1")
                .setDataPhotoSecondaryUnit(Byte.MAX_VALUE)
                .setUrlPhotoSecondaryUnit("url rumah 1")
                .setCreatedBy("DUMMY")
                .setCreatedDate(new Date())
                .setModifiedBy("DUMMY")
                .setModifiedDate(new Date())
                .setStatus(Status.ACTIVE)
                .createSecondaryUnit();
        return secondaryUnitDTOAssembler.toDTO(secondaryUnit1);
    }

    @Override
    public SecondaryUnitDTO findByID(String idSecondaryUnit) {
        Validate.notNull(secondaryUnitRepository);
        SecondaryUnit b = (SecondaryUnit) secondaryUnitRepository.findByID(idSecondaryUnit);
        if (b != null) {
            return secondaryUnitDTOAssembler.toDTO(b);
        }
        return null;
    }

    @Override
    public List<SecondaryUnitDTO> findAll() {
        Validate.notNull(secondaryUnitRepository);
        return secondaryUnitDTOAssembler.toDTOs(secondaryUnitRepository.findAll());
    }

    @Override
    public List<SecondaryUnitDTO> findByParams(Map map) {
        Validate.notNull(secondaryUnitRepository);
        List<SecondaryUnit> listSecondaryUnit = secondaryUnitRepository.findByParams(map);
        if (listSecondaryUnit != null) {
            return (List<SecondaryUnitDTO>) secondaryUnitDTOAssembler.toDTOs(listSecondaryUnit);
        }
        return null;
    }

}
