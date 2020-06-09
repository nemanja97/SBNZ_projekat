package rs.ac.uns.ftn.sbnz.drools.unit;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.models.Coordinate;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;

import static org.junit.jupiter.api.Assertions.*;

public class Finishing {

    private static KieContainer kieContainer;

    private static final String agenda = "finishing";

    @BeforeAll
    static void createContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void finishing() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setId(1L);
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        assertEquals(0, scoredProperties.getPropertyWithScores().size());

        kieSession.insert(ps);
        kieSession.insert(scoredProperties);
        System.out.println(kieSession.fireAllRules());

        assertEquals(1, scoredProperties.getPropertyWithScores().size());
        assertEquals(1L, scoredProperties.getPropertyWithScores().get(0).getProperty().getId());
    }

}
