package rs.ac.uns.ftn.sbnz.drools.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.sbnz.models.Coordinate;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class DistanceScore {

    private static KieContainer kieContainer;

    private static final String agenda = "distance_score_calculation";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void scaleProperties_DISTANCE_KINDERGARTEN_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.KINDERGARTEN);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate kindergarten distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate kindergarten distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_KINDERGARTEN_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.KINDERGARTEN);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate kindergarten distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate kindergarten distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_SCHOOL_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.SCHOOL);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate school distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate school distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_SCHOOL_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.SCHOOL);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate school distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate school distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_UNIVERSITY_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.UNIVERSITY);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate university distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate university distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_UNIVERSITY_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.UNIVERSITY);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate university distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate university distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_HOSPITAL_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.HOSPITAL);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate hospital distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate hospital distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_HOSPITAL_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.HOSPITAL);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate hospital distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate hospital distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_BANK_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.BANK);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate bank distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate bank distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_BANK_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.BANK);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate bank distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate bank distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_PARK_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.PARK);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate park distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate park distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_PARK_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.PARK);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate park distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate park distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_GYM_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.GYM);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate gym distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate gym distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_GYM_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.GYM);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate gym distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate gym distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_RESTAURANT_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.RESTAURANT);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate restaurant distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate restaurant distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_RESTAURANT_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.RESTAURANT);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate restaurant distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate restaurant distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_THEATER_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.THEATER);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate theater distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate theater distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_THEATER_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.THEATER);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate theater distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate theater distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_CINEMA_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.CINEMA);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate cinema distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate cinema distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_CINEMA_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.CINEMA);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate cinema distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate cinema distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_SUPERMARKET_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.SUPERMARKET);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate supermarket distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate supermarket distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_SUPERMARKET_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.SUPERMARKET);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate supermarket distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate supermarket distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_SHOPPING_CENTER_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.SHOPPING_CENTER);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate shopping center distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate shopping center distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_SHOPPING_CENTER_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.SHOPPING_CENTER);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate shopping center distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate shopping center distance score %s - HIGHER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_NIGHT_CLUB_LOWER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(0.0, 0.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.NIGHT_CLUB);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate night club distance score %s - LOWER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + 1, ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate night club distance score %s - LOWER", placeOfInterest.getId())));
    }

    @Test
    public void scaleProperties_DISTANCE_NIGHT_CLUB_HIGHER() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Property p = new Property();
        p.setCoordinate(new Coordinate(1.0, 1.0));
        PropertyWithScore ps = new PropertyWithScore(p);

        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(1L);
        placeOfInterest.setTypeOfPlace(TypeOfPlace.NIGHT_CLUB);
        placeOfInterest.setCoordinate(new Coordinate(0.0, 0.0));

        double score_BEFORE = ps.getScore();
        assertFalse(ps.getScaler().getFiredRules().contains(String.format("Calculate night club distance score %s - HIGHER", placeOfInterest.getId())));

        kieSession.insert(ps);
        kieSession.insert(placeOfInterest);
        System.out.println(kieSession.fireAllRules());

        assertEquals(score_BEFORE + ps.getScaler().getNearbyDistanceFactor() / p.calculateDistance(placeOfInterest), ps.getScore());
        assertTrue(ps.getScaler().getFiredRules().contains(String.format("Calculate night club distance score %s - HIGHER", placeOfInterest.getId())));
    }

}
