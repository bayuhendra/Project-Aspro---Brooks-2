package com.agit.brooks2.project;

import com.agit.brooks2.common.application.HandOverService;
import com.agit.brooks2.common.dto.core.HandOverDTO;
import com.agit.brooks2.domain.handover.HandOverRepository;
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
public class HandOverServiceTest {
    @Autowired
    private HandOverRepository handOverRepository;
    @Autowired
    private HandOverService handOverService;
    
    @Test
    public void testEnd2EndHandOver() {
        Validate.notNull(handOverRepository);
        /* Header*/
        print("Data HandOver Processing");

        /* Incident Record*/
        HandOverDTO m = handOverService.getDummyData();

        System.out.println("Incident Record : " + m.toString());

        handOverService.SaveOrUpdate(m);
        System.out.println("Incident Record has been saved succesfully");

        System.out.println("");
        System.out.println("Find by ID");
        HandOverDTO b = handOverService.findByID(m.getIdHandOver());
        System.out.println("HandOver :" + b.toString());

        System.out.println("");
        System.out.println("Find ALL");
        List<HandOverDTO> listHandOver = (List<HandOverDTO>) handOverService.findAll();
        for (HandOverDTO bt : listHandOver) {
            System.out.println("HandOver :" + bt.toString());

        }

    }

    public void printThisMessage(String msg) {

        System.out.println("------------------------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------------------------");

    }
}
