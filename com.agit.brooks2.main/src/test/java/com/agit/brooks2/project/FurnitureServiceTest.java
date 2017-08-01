/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.project;

import com.agit.brooks2.common.application.FurnitureService;
import com.agit.brooks2.common.dto.masterdata.FurnitureDTO;
import com.agit.brooks2.masterdata.domain.furniture.FurnitureRepository;
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
public class FurnitureServiceTest {

    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private FurnitureService furnitureService;

    @Test
    public void testEnd2EndLowongan() {
        Validate.notNull(furnitureRepository);
        /* Header*/
        print("Data Lowongan Processing");

        /* Incident Record*/
        FurnitureDTO m = furnitureService.getDummyData();

        System.out.println("Incident Record : " + m.toString());

        furnitureService.SaveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        FurnitureDTO b = furnitureService.findByID(m.getIdFurniture());
        System.out.println("Lowongan :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<FurnitureDTO> listLowongan = (List<FurnitureDTO>) furnitureService.findAll();
        for (FurnitureDTO bt : listLowongan) {
            System.out.println("Lowongan :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
