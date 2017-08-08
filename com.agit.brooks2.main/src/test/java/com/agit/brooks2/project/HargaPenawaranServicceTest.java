/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.project;

import com.agit.brooks2.common.application.HargaPenawaranService;
import com.agit.brooks2.common.dto.masterdata.HargaPenawaranDTO;
import com.agit.brooks2.masterdata.domain.HargaPenawaranRepository;
import java.util.List;
import static org.activiti.engine.impl.scripting.JuelScriptEngine.print;
import org.apache.commons.lang.Validate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author 3AD
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/brooks2-context-application.xml",
    "classpath:/brooks2-context-infrastructure.xml"})
public class HargaPenawaranServicceTest {

    @Autowired
    private HargaPenawaranRepository furnitureRepository;
    @Autowired
    private HargaPenawaranService furnitureService;

    @Test
    public void testEnd2EndLowongan() {
        Validate.notNull(furnitureRepository);
        /* Header*/
        print("Data Lowongan Processing");

        /* Incident Record*/
        HargaPenawaranDTO m = furnitureService.getDummy();

        System.out.println("Incident Record : " + m.toString());

        furnitureService.saveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        HargaPenawaranDTO b = furnitureService.findByID(m.getHargaPenawaranID());
        System.out.println("Lowongan :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<HargaPenawaranDTO> listLowongan = (List<HargaPenawaranDTO>) furnitureService.findAll();
        for (HargaPenawaranDTO bt : listLowongan) {
            System.out.println("Lowongan :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
