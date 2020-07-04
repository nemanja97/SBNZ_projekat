package rs.ac.uns.ftn.sbnz.drools.unit;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.*;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PropertyStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class Reports {

    private static KieContainer kieContainer;

    private static final String agenda = "reports";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void getReport() throws ParseException {
        KieSession kieSession = kieContainer.getKieBase("KBase4").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Property p1 = new Property();
        p1.setPrice(1000);
        p1.setStatus(PropertyStatus.SOLD);
        p1.setModifiedDate(simpleDateFormat.parse("05.01.2020"));
        Property p2 = new Property();
        p2.setPrice(3000);
        p2.setStatus(PropertyStatus.SOLD);
        p2.setModifiedDate(simpleDateFormat.parse("10.01.2020"));
        Property p3 = new Property();
        p3.setPrice(5000);
        p3.setStatus(PropertyStatus.SOLD);
        p3.setModifiedDate(simpleDateFormat.parse("15.01.2020"));
        Property p4 = new Property();
        p4.setPrice(5000);
        p4.setStatus(PropertyStatus.SOLD);
        p4.setModifiedDate(simpleDateFormat.parse("15.03.2020"));

        FinancialReport financialReport = new FinancialReport(
                simpleDateFormat.parse("01.01.2020"),
                simpleDateFormat.parse("01.02.2020"));

        kieSession.insert(p1);
        kieSession.insert(p2);
        kieSession.insert(p3);
        kieSession.insert(p4);
        kieSession.insert(financialReport);
        kieSession.fireAllRules();

        assertEquals(3, financialReport.getCount());
        assertEquals(9000, financialReport.getTotalPrice());
        assertEquals(1000, financialReport.getMinPrice());
        assertEquals(5000, financialReport.getMaxPrice());
        assertEquals((p1.getPrice() + p2.getPrice() + p3.getPrice()) / 3.0, financialReport.getAvgPrice());
        assertEquals(simpleDateFormat.parse("05.01.2020"), financialReport.getPropertyList().get(0).getModifiedDate());
        assertEquals(simpleDateFormat.parse("10.01.2020"), financialReport.getPropertyList().get(1).getModifiedDate());
        assertEquals(simpleDateFormat.parse("15.01.2020"), financialReport.getPropertyList().get(2).getModifiedDate());
    }
}
