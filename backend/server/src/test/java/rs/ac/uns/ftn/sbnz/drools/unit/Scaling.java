package rs.ac.uns.ftn.sbnz.drools.unit;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.*;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.Interest;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class Scaling {

    private static KieContainer kieContainer;

    private static final String agenda = "scaling";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void scaleProperties_HAS_VEHICLE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, true, Lists.emptyList()),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(5.0, ps.getScaler().getNearbyDistanceFactor());
        assertFalse(ps.getScaler().getFiredRules().contains("Has vehicle"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(10.0, ps.getScaler().getNearbyDistanceFactor());
        assertTrue(ps.getScaler().getFiredRules().contains("Has vehicle"));
    }

    @Test
    public void scaleProperties_EXPECTING_KIDS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, false, Lists.emptyList()),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getHospitalScale());
        assertEquals(1.0, ps.getScaler().getKindergartenScale());
        assertEquals(1.0, ps.getScaler().getSchoolScale());
        assertEquals(1.0, ps.getScaler().getSupermarketScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Expecting kids"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(2.5, ps.getScaler().getHospitalScale());
        assertEquals(2.5, ps.getScaler().getKindergartenScale());
        assertEquals(2.5, ps.getScaler().getSchoolScale());
        assertEquals(1.5, ps.getScaler().getSupermarketScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Expecting kids"));
    }

    @Test
    public void scaleProperties_YOUNGER_PERSON() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(1, 0, 0,
                        false, false, Lists.emptyList()),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getUniversityScale());
        assertEquals(1.0, ps.getScaler().getNightClubScale());
        assertEquals(1.0, ps.getScaler().getShoppingCenterScale());
        assertEquals(1.0, ps.getScaler().getHighSpeedInternetScale());
        assertEquals(1.0, ps.getScaler().getCableReadyScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Younger person"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(1.5, ps.getScaler().getUniversityScale());
        assertEquals(1.5, ps.getScaler().getNightClubScale());
        assertEquals(1.5, ps.getScaler().getShoppingCenterScale());
        assertEquals(2.0, ps.getScaler().getHighSpeedInternetScale());
        assertEquals(2.0, ps.getScaler().getCableReadyScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Younger person"));
    }

    @Test
    public void scaleProperties_MIDDLE_AGED_PERSON() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 1, 0,
                        false, false, Lists.emptyList()),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getBankScale());
        assertEquals(1.0, ps.getScaler().getRestaurantScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Middle aged person"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(1.5, ps.getScaler().getBankScale());
        assertEquals(1.5, ps.getScaler().getRestaurantScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Middle aged person"));
    }

    @Test
    public void scaleProperties_OLDER_PERSON() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 1,
                        false, false, Lists.emptyList()),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getHospitalScale());
        assertEquals(1.0, ps.getScaler().getShoppingCenterScale());
        assertEquals(1.0, ps.getScaler().getElevatorScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Older person"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(3.5, ps.getScaler().getHospitalScale());
        assertEquals(3.5, ps.getScaler().getShoppingCenterScale());
        assertEquals(6.0, ps.getScaler().getElevatorScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Older person"));
    }

    @Test
    public void scaleProperties_FOOD_INTEREST() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.list(Interest.FOOD)),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getRestaurantScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in food"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(2.5, ps.getScaler().getRestaurantScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in food"));
    }

    @Test
    public void scaleProperties_NATURE_INTEREST() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.list(Interest.NATURE)),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getParkScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in nature"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(2.5, ps.getScaler().getParkScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in nature"));
    }

    @Test
    public void scaleProperties_SPORT_INTEREST() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.list(Interest.SPORT)),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getParkScale());
        assertEquals(1.0, ps.getScaler().getGymScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in sport"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(1.75, ps.getScaler().getParkScale());
        assertEquals(2.25, ps.getScaler().getGymScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in sport"));
    }

    @Test
    public void scaleProperties_CULTURE_INTEREST() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.list(Interest.CULTURE)),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getTheaterScale());
        assertEquals(1.0, ps.getScaler().getCinemaScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in culture"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(1.75, ps.getScaler().getTheaterScale());
        assertEquals(2.25, ps.getScaler().getCinemaScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in culture"));
    }

    @Test
    public void scaleProperties_NIGHT_LIFE_INTEREST() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.list(Interest.NIGHT_LIFE)),
                new PropertyInformation(0, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getNightClubScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in night life"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(2.0, ps.getScaler().getNightClubScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in night life"));
    }

    @Test
    public void scaleProperties_HIGHER_PRICE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.emptyList()),
                new PropertyInformation(20000, 0,
                        0, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getSecurityScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in higher price properties"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(6.0, ps.getScaler().getSecurityScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in higher price properties"));
    }

    @Test
    public void scaleProperties_LARGER_SIZE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        false, false, Lists.emptyList()),
                new PropertyInformation(0, 0,
                        200, 0,
                        0, 0,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        PropertyWithScore ps = new PropertyWithScore(new Property());

        assertEquals(1.0, ps.getScaler().getHeatingScale());
        assertFalse(ps.getScaler().getFiredRules().contains("Interested in larger properties"));

        kieSession.insert(smartSearch);
        kieSession.insert(ps);
        kieSession.fireAllRules();

        assertEquals(3.0, ps.getScaler().getHeatingScale());
        assertTrue(ps.getScaler().getFiredRules().contains("Interested in larger properties"));
    }
}
