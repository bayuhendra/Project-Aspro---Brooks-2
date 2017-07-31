package com.agit.brooks2.application.impl;

import com.agit.brooks2.common.application.LowonganService;
import com.agit.brooks2.common.dto.crm.LowonganDTO;
import com.agit.brooks2.domain.crm.Lowongan;
import com.agit.brooks2.domain.crm.LowonganBuilder;
import com.agit.brooks2.domain.crm.LowonganRepository;
import com.agit.brooks2.interfaces.web.facade.dto.assembler.crm.LowonganDTOAssembler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;

/**
 *
 * @author Bayu Hendra Setiawan
 */
public class LowonganServiceImpl implements LowonganService {

    private LowonganRepository lowonganRepository;
    private LowonganDTOAssembler lowonganDTOAssembler;

    public void setLowonganRepository(LowonganRepository lowonganRepository) {
        this.lowonganRepository = lowonganRepository;
    }

    public void setLowonganDTOAssembler(LowonganDTOAssembler lowonganDTOAssembler) {
        this.lowonganDTOAssembler = lowonganDTOAssembler;
    }

    @Override
    public void SaveOrUpdate(LowonganDTO lowongan) {
        Lowongan t = lowonganRepository.findByID(lowongan.getIdLowongan());

        if (t == null) {
            t = lowonganDTOAssembler.toDomain(lowongan);
        } else {
            Lowongan newLowongan = lowonganDTOAssembler.toDomain(lowongan);
            t.assignNewLowongan(newLowongan);

        }
        lowonganRepository.saveOrUpdate(t);
    }

    @Override
    public void deleteData(LowonganDTO lowongan) {
        Lowongan p = lowonganDTOAssembler.toDomain(lowongan);
        lowonganRepository.deleteData(p);
    }

    @Override
    public LowonganDTO getDummyData() {

        /* Riwayat Apply Mahasiswa Dummy */
 /*dummy1*/
 /* Lowongan Dummy1 */
        Lowongan lowongan1 = new LowonganBuilder()
                .setIdLowongan("L001")
                .setNamaLowongan("namaLow1")
                .setDeskripsiLowongan("descLow1")
                .setTanggalMulai(new Date())
                .setTanggalBerakhir(new Date())
                .setMinatPekerjaan("minat1")
                .setPersyaratan("persyaratan1")
                .setLokasiKerja("lokasi1")
                .setGaji("Rp 2.000.000 - Rp 3.000.000")
                .setCreatedBy("admin")
                .setUserID(Long.MIN_VALUE)
                .setCreatedDate(new Date())
                .setModifiedBy("modified by")
                .setModifiedDate(new Date())
                .createLowongan();
        return lowonganDTOAssembler.toDTO(lowongan1);

//        Lowongan lowongan2 = new LowonganBuilder()
//                .setIdLowongan("L002")
//                .setNamaLowongan("namaLow2")
//                .setDeskripsiLowongan("descLow2")
//                .setTanggalMulai(new Date())
//                .setTanggalBerakhir(new Date())
//                .setMinatPekerjaan("minat2")
//                .setPersyaratan("persyaratan2")
//                .setLokasiKerja("lokasi2")
//                .setGaji("Rp 2.000.000 - Rp 3.000.000")
//                .setIdUser("User2")
//                .setListLowonganStatuses(lowonganStatuses)
//                .setListRiwayatApplyMahasiswa(riwayatApplyMahasiswas)
//                .setCreatedBy("admin")
//                .setCreatedDate(new Date())
//                .setModifiedBy("user")
//                .setModifiedDate(new Date())
//                .createLowongan();
//        return lowonganDTOAssembler.toDTO(lowongan2);
    }

    @Override
    public LowonganDTO findByID(String idLowongan) {
        Validate.notNull(lowonganRepository);
        Lowongan b = (Lowongan) lowonganRepository.findByID(idLowongan);
        if (b != null) {
            return lowonganDTOAssembler.toDTO(b);
        }
        return null;
    }

    @Override
    public List<LowonganDTO> findByParams(Map map) {
        Validate.notNull(lowonganRepository);
        List<Lowongan> listLowongan = lowonganRepository.findByParams(map);
        if (listLowongan != null) {
            return (List<LowonganDTO>) lowonganDTOAssembler.toDTOs(listLowongan);
        }
        return null;
    }

    @Override
    public List<LowonganDTO> findAll() {
        Validate.notNull(lowonganRepository);
        return lowonganDTOAssembler.toDTOs(lowonganRepository.findAll());
    }

}
