package com.agit.brooks2.project;

import com.agit.brooks2.common.application.VendorService;
import com.agit.brooks2.common.dto.masterdata.VendorDTO;
import com.agit.brooks2.masterdata.domain.vendor.VendorRepository;
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
public class VendorServiceTest {

    @Autowired
    private VendorRepository furnitureRepository;
    @Autowired
    private VendorService furnitureService;

    @Test
    public void testEnd2EndLowongan() {
        Validate.notNull(furnitureRepository);
        /* Header*/
        print("Data Lowongan Processing");

        /* Incident Record*/
        VendorDTO m = furnitureService.getDummy();

        System.out.println("Incident Record : " + m.toString());

        furnitureService.saveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        VendorDTO b = furnitureService.findByID(m.getVendorID());
        System.out.println("Lowongan :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<VendorDTO> listLowongan = (List<VendorDTO>) furnitureService.findAll();
        for (VendorDTO bt : listLowongan) {
            System.out.println("Lowongan :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
