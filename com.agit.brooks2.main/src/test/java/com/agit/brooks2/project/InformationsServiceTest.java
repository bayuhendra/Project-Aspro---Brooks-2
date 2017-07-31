package com.agit.brooks2.project;

import com.agit.brooks2.common.application.InformationsService;
import com.agit.brooks2.common.dto.masterdata.InformationsDTO;
import com.agit.brooks2.masterdata.domain.information.InformationsRepository;
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
public class InformationsServiceTest {

    @Autowired
    private InformationsRepository informationsRepository;
    @Autowired
    private InformationsService informationsService;

    @Test
    public void testEnd2EndLowongan() {
        Validate.notNull(informationsRepository);
        /* Header*/
        print("Data Lowongan Processing");

        /* Incident Record*/
        InformationsDTO m = informationsService.getDummyData();

        System.out.println("Incident Record : " + m.toString());

        informationsService.SaveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        InformationsDTO b = informationsService.findByID(m.getIdNews());
        System.out.println("Lowongan :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<InformationsDTO> listLowongan = (List<InformationsDTO>) informationsService.findAll();
        for (InformationsDTO bt : listLowongan) {
            System.out.println("Lowongan :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
