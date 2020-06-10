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
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class PetScore {

    private static KieContainer kieContainer;

    private static final String agenda = "pet_score_calculation";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void scaleProperties_PETS_CATS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAllowedPets(Set.of(PetStatus.CATS));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate pet score - CATS"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate pet score - CATS"));
    }

    @Test
    public void scaleProperties_PETS_DOGS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAllowedPets(Set.of(PetStatus.DOGS));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate pet score - DOGS"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate pet score - DOGS"));
    }

    @Test
    public void scaleProperties_PETS_IN_AQUARIUM() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAllowedPets(Set.of(PetStatus.IN_AQUARIUM));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate pet score - IN_AQUARIUM"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 0.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate pet score - IN_AQUARIUM"));
    }

    @Test
    public void scaleProperties_PETS_IN_TERRARIUM() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setAllowedPets(Set.of(PetStatus.IN_TERRARIUM));
        PropertyWithScore ps = new PropertyWithScore(p);

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains("Calculate pet score - IN_TERRARIUM"));

        kieSession.insert(ps);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 0.5, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains("Calculate pet score - IN_TERRARIUM"));
    }
}
