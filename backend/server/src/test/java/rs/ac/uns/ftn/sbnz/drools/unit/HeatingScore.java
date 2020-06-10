package rs.ac.uns.ftn.sbnz.drools.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class HeatingScore {

    private static KieContainer kieContainer;

    private static final String agenda = "heating_score_calculation";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void scaleProperties_HEATING_FURNACE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setHeating(Heating.FURNACE);
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate heating score - FURNACE"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate heating score - FURNACE"));
    }

    @Test
    public void scaleProperties_HEATING_BOILER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setHeating(Heating.BOILER);
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate heating score - BOILER"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 2, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate heating score - BOILER"));
    }

    @Test
    public void scaleProperties_HEATING_HEAT_PUMP() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setHeating(Heating.HEAT_PUMP);
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate heating score - HEAT_PUMP"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 3, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate heating score - HEAT_PUMP"));
    }

    @Test
    public void scaleProperties_HEATING_HYBRID() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setHeating(Heating.HYBRID);
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate heating score - HYBRID"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 4, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate heating score - HYBRID"));
    }

    @Test
    public void scaleProperties_HEATING_DUCTLESS_MINI_SPLITS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setHeating(Heating.DUCTLESS_MINI_SPLITS);
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate heating score - DUCTLESS_MINI_SPLITS"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate heating score - DUCTLESS_MINI_SPLITS"));
    }

    @Test
    public void scaleProperties_HEATING_RADIANT() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setHeating(Heating.RADIANT);
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate heating score - RADIANT"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 6, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate heating score - RADIANT"));
    }
}
