package rs.ac.uns.ftn.sbnz.drools.unit;

import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.events.MoreInfoClickedEvent;
import rs.ac.uns.ftn.sbnz.models.enums.PriceRecommendation;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class Analytics {

    private static KieContainer kieContainer;

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void analytics_NONE() {
        KieSession kieSession = kieContainer.getKieBase("KBase1").newKieSession();

        Property p = new Property();
        p.setId(1L);

        kieSession.insert(p);
        System.out.println(kieSession.fireAllRules());

        assertEquals(PriceRecommendation.LOWER, p.getPriceRecommendation());
        kieSession.dispose();
    }

    @Test
    public void analytics_LOW() {
        KieSession kieSession = kieContainer.getKieBase("KBase1").newKieSession();

        Property p = new Property();
        p.setId(1L);

        for (int i = 0; i < 10; i++) {
            kieSession.insert(new MoreInfoClickedEvent(new Date(), p.getId()));
        }

        kieSession.insert(p);
        System.out.println(kieSession.fireAllRules());

        assertEquals(PriceRecommendation.LOWER, p.getPriceRecommendation());
        kieSession.dispose();
    }

    @Test
    public void analytics_MEDIUM() {
        KieSessionConfiguration ksconf = KieServices.Factory.get().newKieSessionConfiguration();
        ksconf.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));

        KieSession kieSession = kieContainer.getKieBase("KBase1").newKieSession(ksconf, null);
        SessionPseudoClock clock = kieSession.getSessionClock();

        Property p = new Property();
        p.setId(1L);

        for (int i = 0; i < 20; i++) {
            kieSession.insert(new MoreInfoClickedEvent(new Date(clock.getCurrentTime()), p.getId()));
            clock.advanceTime(1, TimeUnit.MINUTES);
        }

        kieSession.insert(p);
        System.out.println(kieSession.getFactCount());
        System.out.println(kieSession.fireAllRules());

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(MoreInfoClickedEvent.class));
        assertEquals(20, newEvents.size());
        assertEquals(PriceRecommendation.KEEP, p.getPriceRecommendation());
        kieSession.dispose();
    }

    @Test
    public void analytics_HIGH() {
        KieSessionConfiguration ksconf = KieServices.Factory.get().newKieSessionConfiguration();
        ksconf.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));

        KieSession kieSession = kieContainer.getKieBase("KBase1").newKieSession(ksconf, null);
        SessionPseudoClock clock = kieSession.getSessionClock();

        Property p = new Property();
        p.setId(1L);

        for (int i = 0; i < 30; i++) {
            kieSession.insert(new MoreInfoClickedEvent(new Date(clock.getCurrentTime()), p.getId()));
            clock.advanceTime(1, TimeUnit.MINUTES);
        }

        kieSession.insert(p);
        System.out.println(kieSession.getFactCount());
        System.out.println(kieSession.fireAllRules());

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(MoreInfoClickedEvent.class));
        assertEquals(30, newEvents.size());
        assertEquals(PriceRecommendation.HIGHER, p.getPriceRecommendation());
        kieSession.dispose();
    }

    @Test
    public void analytics_EXPIRATION_WINDOW() {
        KieSessionConfiguration ksconf = KieServices.Factory.get().newKieSessionConfiguration();
        ksconf.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));

        KieSession kieSession = kieContainer.getKieBase("KBase1").newKieSession(ksconf, null);
        SessionPseudoClock clock = kieSession.getSessionClock();

        Property p = new Property();
        p.setId(1L);

        kieSession.insert(new MoreInfoClickedEvent(new Date(clock.getCurrentTime()), p.getId()));
        clock.advanceTime(14, TimeUnit.DAYS);

        for (int i = 0; i < 9; i++) {
            kieSession.insert(new MoreInfoClickedEvent(new Date(clock.getCurrentTime()), p.getId()));
            clock.advanceTime(1, TimeUnit.MINUTES);
        }

        kieSession.insert(p);
        System.out.println(kieSession.getFactCount());
        System.out.println(kieSession.fireAllRules());

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(MoreInfoClickedEvent.class));
        assertEquals(9, newEvents.size());
        assertEquals(PriceRecommendation.LOWER, p.getPriceRecommendation());
        kieSession.dispose();
    }
}
