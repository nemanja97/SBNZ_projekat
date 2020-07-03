package rs.ac.uns.ftn.sbnz.drools.unit;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.sbnz.models.Coordinate;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class Finishing {

    private static KieContainer kieContainer;

    private static final String agenda = "finishing";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void finishing() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PropertyWithScore ps1 = new PropertyWithScore(new Property());
        ps1.setScore(1);
        ps1.getProperty().setId(1L);

        PropertyWithScore ps2 = new PropertyWithScore(new Property());
        ps2.setScore(3);
        ps2.getProperty().setId(2L);

        PropertyWithScore ps3 = new PropertyWithScore(new Property());
        ps3.setScore(2);
        ps3.getProperty().setId(3L);

        PropertyWithScore ps4 = new PropertyWithScore(new Property());
        ps4.setScore(4.2);
        ps4.getProperty().setId(4L);

        PropertyWithScore ps5 = new PropertyWithScore(new Property());
        ps5.setScore(5.2);
        ps5.getProperty().setId(5L);

        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        assertEquals(0, scoredProperties.getPropertyWithScores().size());

        kieSession.insert(ps1);
        kieSession.insert(ps2);
        kieSession.insert(ps3);
        kieSession.insert(ps4);
        kieSession.insert(ps5);

        kieSession.insert(scoredProperties);
        System.out.println(kieSession.fireAllRules());

        assertEquals(5, scoredProperties.getPropertyWithScores().size());
        assertEquals(5L, scoredProperties.getPropertyWithScores().get(0).getProperty().getId());
        assertEquals(4L, scoredProperties.getPropertyWithScores().get(1).getProperty().getId());
        assertEquals(2L, scoredProperties.getPropertyWithScores().get(2).getProperty().getId());
        assertEquals(3L, scoredProperties.getPropertyWithScores().get(3).getProperty().getId());
        assertEquals(1L, scoredProperties.getPropertyWithScores().get(4).getProperty().getId());
    }

}
