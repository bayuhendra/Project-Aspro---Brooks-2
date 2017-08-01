package com.agit.brooks2.application.impl;

import com.agit.brooks2.common.application.HandOverService;
import com.agit.brooks2.common.dto.core.HandOverDTO;
import com.agit.brooks2.domain.handover.HandOver;
import com.agit.brooks2.domain.handover.HandOverBuilder;
import com.agit.brooks2.domain.handover.HandOverRepository;
import com.agit.brooks2.interfaces.web.facade.dto.assembler.HandOverDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.sql.Time;
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
public class HandOverServiceImpl implements HandOverService {
    private HandOverRepository handOverRepository;
    private HandOverDTOAssembler handOverDTOAssembler;

    public void setHandOverRepository(HandOverRepository handOverRepository) {
        this.handOverRepository = handOverRepository;
    }

    public void setHandOverDTOAssembler(HandOverDTOAssembler handOverDTOAssembler) {
        this.handOverDTOAssembler = handOverDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(HandOverDTO handOver) {
        HandOver t = handOverRepository.findByID(handOver.getIdHandOver());

        if (t == null) {
            t = handOverDTOAssembler.toDomain(handOver);
        } else {
            HandOver newHandOver = handOverDTOAssembler.toDomain(handOver);
            t.assignNewHandOver(newHandOver);

        }
        handOverRepository.saveOrUpdate(t);
    }

    @Override
    public void deleteData(HandOverDTO handOver) {
        HandOver p = handOverDTOAssembler.toDomain(handOver);
        handOverRepository.deleteData(p);
    }

    @Override
    public HandOverDTO getDummyData() {
        HandOver handOver1 = new HandOverBuilder()
                .setIdHandOver("HO0001")
                .setStartTime(new Date())
                .setEndTime(new Date())
                .setStartDate(new Date())
                .setEndDate(new Date())
                .setCreatedBy("DUMMY")
                .setCreatedDate(new Date())
                .setModifiedBy("DUMMY")
                .setModifiedDate(new Date())
                .setStatus(Status.ACTIVE)
                .createHandOver();
        return handOverDTOAssembler.toDTO(handOver1);
                
    }

    @Override
    public HandOverDTO findByID(String idHandOver) {
        Validate.notNull(handOverRepository);
        HandOver b = (HandOver) handOverRepository.findByID(idHandOver);
        if (b != null) {
            return handOverDTOAssembler.toDTO(b);
        }
        return null;
    }

    @Override
    public List<HandOverDTO> findAll() {
        Validate.notNull(handOverRepository);
        return handOverDTOAssembler.toDTOs(handOverRepository.findAll());
    }

    @Override
    public List<HandOverDTO> findByParams(Map map) {
        Validate.notNull(handOverRepository);
        List<HandOver> listHandOver = handOverRepository.findByParams(map);
        if (listHandOver != null) {
            return (List<HandOverDTO>) handOverDTOAssembler.toDTOs(listHandOver);
        }
        return null;
    }
    
}
