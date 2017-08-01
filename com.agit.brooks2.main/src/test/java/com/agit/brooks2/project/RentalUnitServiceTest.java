package com.agit.brooks2.project;

import com.agit.brooks2.common.application.RentalUnitService;
import com.agit.brooks2.common.dto.core.RentalUnitDTO;
import com.agit.brooks2.domain.rentalunit.RentalUnitRepository;
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
public class RentalUnitServiceTest {
    
    @Autowired
    private RentalUnitRepository rentalUnitRepository;
    @Autowired
    private RentalUnitService rentalUnitService;
    
    @Test
    public void testEnd2EndRentalUnit() {
        Validate.notNull(rentalUnitRepository);
        /* Header*/
        print("Data RentalUnit Processing");

        /* Incident Record*/
        RentalUnitDTO m = rentalUnitService.getDummyData();

        System.out.println("Incident Record : " + m.toString());

        rentalUnitService.SaveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        RentalUnitDTO b = rentalUnitService.findByID(m.getIdRentalUnit());
        System.out.println("RentalUnit :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<RentalUnitDTO> listRentalUnit = (List<RentalUnitDTO>) rentalUnitService.findAll();
        for (RentalUnitDTO bt : listRentalUnit) {
            System.out.println("RentalUnit :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
