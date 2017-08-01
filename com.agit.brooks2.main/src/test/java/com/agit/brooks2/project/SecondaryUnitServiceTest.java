package com.agit.brooks2.project;

import com.agit.brooks2.common.application.SecondaryUnitService;
import com.agit.brooks2.common.dto.core.SecondaryUnitDTO;
import com.agit.brooks2.domain.secondaryunit.SecondaryUnitRepository;
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
 * @author Zaky
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/brooks2-context-application.xml",
    "classpath:/brooks2-context-infrastructure.xml"})
public class SecondaryUnitServiceTest  {
    @Autowired
    private SecondaryUnitRepository secondaryUnitRepository;
    @Autowired
    private SecondaryUnitService secondaryUnitService;
    
    @Test
    public void testEnd2EndSecondaryUnit() {
        Validate.notNull(secondaryUnitRepository);
        /* Header*/
        print("Data SecondaryUnit Processing");

        /* Incident Record*/
        SecondaryUnitDTO m = secondaryUnitService.getDummyData();

        System.out.println("Incident Record : " + m.toString());

        secondaryUnitService.SaveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        SecondaryUnitDTO b = secondaryUnitService.findByID(m.getIdSecondaryUnit());
        System.out.println("SecondaryUnit :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<SecondaryUnitDTO> listSecondaryUnit = (List<SecondaryUnitDTO>) secondaryUnitService.findAll();
        for (SecondaryUnitDTO bt : listSecondaryUnit) {
            System.out.println("SecondaryUnit :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
