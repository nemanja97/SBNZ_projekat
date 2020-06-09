package rs.ac.uns.ftn.sbnz.drools.unit;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.*;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AmenityScore {

    private static KieContainer kieContainer;

    private static final String agenda = "amenity_score_calculation";

    @BeforeAll
    static void createContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void scaleProperties_AMENITY_ELEVATOR() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.ELEVATOR));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - ELEVATOR"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 2, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - ELEVATOR"));
    }

    @Test
    public void scaleProperties_AMENITY_AIR_CONDITIONING() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.AIR_CONDITIONING));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - AIR_CONDITIONING"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 1.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - AIR_CONDITIONING"));
    }

    @Test
    public void scaleProperties_AMENITY_CABLE_READY() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.CABLE_READY));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - CABLE_READY"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 2, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - CABLE_READY"));
    }

    @Test
    public void scaleProperties_AMENITY_HIGH_SPEED_INTERNET_ACCESS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.HIGH_SPEED_INTERNET_ACCESS));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - HIGH_SPEED_INTERNET_ACCESS"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 2.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - HIGH_SPEED_INTERNET_ACCESS"));
    }

    @Test
    public void scaleProperties_AMENITY_SWIMMING_POOL() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.SWIMMING_POOL));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - SWIMMING_POOL"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 4, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - SWIMMING_POOL"));
    }

    @Test
    public void scaleProperties_AMENITY_GARAGE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.GARAGE));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - GARAGE"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 2.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - GARAGE"));
    }

    @Test
    public void scaleProperties_AMENITY_SECURITY() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.SECURITY));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - SECURITY"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 6, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - SECURITY"));
    }

    @Test
    public void scaleProperties_AMENITY_GATED() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAmenities(Set.of(Amenity.GATED));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate amenity score - GATED"));

        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(score_BEFORE + 3.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate amenity score - GATED"));
    }
}
