package com.agit.brooks2.masterdata.application.impl;

import com.agit.brooks2.common.application.HargaPenawaranService;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTO;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTOBuilder;
import com.agit.brooks2.masterdata.domain.HargaPenawaran;
import com.agit.brooks2.masterdata.domain.HargaPenawaranRepository;
import com.agit.brooks2.masterdata.interfaces.web.facade.dto.assembler.HargaPenawaranDTOAssembler;
import com.agit.brooks2.shared.status.Status;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;

/**
 *
 * @author 3AD
 */
public class HargaPenawaranServiceImpl implements HargaPenawaranService {

    private HargaPenawaranRepository hargaPenawaranRepository;
    private HargaPenawaranDTOAssembler hargaPenawaranDTOAssembler;

    public void setHargaPenawaranRepository(HargaPenawaranRepository hargaPenawaranRepository) {
        this.hargaPenawaranRepository = hargaPenawaranRepository;
    }

    public void setHargaPenawaranDTOAssembler(HargaPenawaranDTOAssembler hargaPenawaranDTOAssembler) {
        this.hargaPenawaranDTOAssembler = hargaPenawaranDTOAssembler;
    }

    @Override
    public void saveOrUpdate(HargaPenawaranDTO hargaPenawaran) {
        HargaPenawaran p = hargaPenawaranRepository.findByID(hargaPenawaran.getHargaPenawaranID());

        if (p == null) {
            p = hargaPenawaranDTOAssembler.toDomain(hargaPenawaran);
        } else {
            HargaPenawaran newPenawaran = hargaPenawaranDTOAssembler.toDomain(hargaPenawaran);
            p.assignNewHargaPenawaran(newPenawaran);
        }
        hargaPenawaranRepository.saveOrUpadate(p);
    }

    @Override
    public void delete(HargaPenawaranDTO hargaPenawaran) {
        HargaPenawaran p = hargaPenawaranDTOAssembler.toDomain(hargaPenawaran);
        hargaPenawaranRepository.delete(p);
    }

    @Override
    public HargaPenawaranDTO findByID(String hargaPenawaranID) {
        Validate.notNull(hargaPenawaranRepository);
        HargaPenawaran p = (HargaPenawaran) hargaPenawaranRepository.findByID(hargaPenawaranID);
        if (p != null) {
            return hargaPenawaranDTOAssembler.toDTO(p);
        }
        return null;
    }

    @Override
    public List<HargaPenawaranDTO> findAll() {
        Validate.notNull(hargaPenawaranRepository);
        return hargaPenawaranDTOAssembler.toDTOs(hargaPenawaranRepository.findAll());
    }

    @Override
    public List<HargaPenawaranDTO> findByParams(Map map) {
        Validate.notNull(hargaPenawaranRepository);
        List<HargaPenawaran> listHarga = hargaPenawaranRepository.findByParams(map);
        if (listHarga != null) {
            return (List<HargaPenawaranDTO>) hargaPenawaranDTOAssembler.toDTOs(listHarga);
        }
        return null;
    }

    @Override
    public HargaPenawaranDTO getDummy() {
        HargaPenawaranDTO hargaPenawaranDTO = new HargaPenawaranDTOBuilder()
                .setHargaPenawaranID("A11")
                .setHarga("5000-100000")
                .setStatus(Status.ACTIVE)
                .setCreatedBy(null)
                .setCreatedDate(null)
                .setModifiedBy(null)
                .setModifiedDate(null)
                .createHargaPenawaranDTO();
        return hargaPenawaranDTO;
    }

}
