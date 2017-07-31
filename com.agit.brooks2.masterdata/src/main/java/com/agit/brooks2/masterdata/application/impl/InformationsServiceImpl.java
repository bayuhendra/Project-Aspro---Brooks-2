package com.agit.brooks2.masterdata.application.impl;

import com.agit.brooks2.common.application.InformationsService;
import com.agit.brooks2.common.dto.masterdata.InformationsDTO;
import com.agit.brooks2.masterdata.domain.information.Informations;
import com.agit.brooks2.masterdata.domain.information.InformationsBuilder;
import com.agit.brooks2.masterdata.domain.information.InformationsRepository;
import com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler.InformationsDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author 3AD
 */
@Controller
public class InformationsServiceImpl implements InformationsService {

    private InformationsRepository informationsRepository;
    private InformationsDTOAssembler informationsDTOAssembler;

    public void setInformationsRepository(InformationsRepository informationsRepository) {
        this.informationsRepository = informationsRepository;
    }

    public void setInformationsDTOAssembler(InformationsDTOAssembler informationsDTOAssembler) {
        this.informationsDTOAssembler = informationsDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(InformationsDTO informations) {
        Informations p = informationsRepository.findByID(informations.getIdNews());

        if (p == null) {
            p = informationsDTOAssembler.toDomain(informations);
        } else {
            Informations newInformations = informationsDTOAssembler.toDomain(informations);
            p.assignNewInformations(newInformations);
        }
        informationsRepository.saveOrUpdate(p);
    }

    @Override
    public void deleteData(InformationsDTO informations) {
        Informations p = informationsDTOAssembler.toDomain(informations);
        informationsRepository.delete(p);
    }

    @Override
    public InformationsDTO getDummyData() {
        Informations informations = new InformationsBuilder()
                .setIdNews("111")
                .setTittleNews("TittleNews")
                .setDescriptionNews("DescriptionNews")
                .setStatusProject(Status.ACTIVE)
                .setPhotoInformation(null)
                .setUrlPhotoInformation("UrlPhotoInformation")
                .setStatus(Status.ACTIVE)
                .setCreatedBy("System")
                .setCreatedDate(new Date())
                .setModifiedBy("System")
                .setModifiedDate(new Date())
                .createInformations();
        return informationsDTOAssembler.toDTO(informations);
    }

    @Override
    public InformationsDTO findByID(String idNews) {
        Validate.notNull(informationsRepository);
        Informations p = (Informations) informationsRepository.findByID(idNews);
        if (p != null) {
            return informationsDTOAssembler.toDTO(p);
        }
        return null;
    }

    @Override
    public List<InformationsDTO> findAll() {
        Validate.notNull(informationsRepository);
        return informationsDTOAssembler.toDTOs(informationsRepository.findAll());
    }

    @Override
    public List<InformationsDTO> findByParams(Map map) {
        Validate.notNull(informationsRepository);
        List<Informations> listInformations = informationsRepository.findByParams(map);
        if (listInformations != null) {
            return (List<InformationsDTO>) informationsDTOAssembler.toDTOs(listInformations);
        }
        return null;
    }

}
