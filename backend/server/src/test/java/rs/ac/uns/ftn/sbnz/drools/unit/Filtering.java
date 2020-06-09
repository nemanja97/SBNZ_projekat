package rs.ac.uns.ftn.sbnz.drools.unit;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PersonalInformation;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyInformation;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.models.drools.SmartSearch;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.models.enums.PropertyStatus;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Filtering {

    private static KieContainer kieContainer;
    
    private static final String agenda = "filtering";

    @BeforeAll
    static void createContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void filterProperties_PRICE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 600,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(1, rulesFired);
    }

    @Test
    public void filterProperties_PRICE_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 1,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    @Test
    public void filterProperties_SIZE() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 60,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(1, rulesFired);
    }

    @Test
    public void filterProperties_SIZE_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 1,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    @Test
    public void filterProperties_BEDS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 5,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(1, rulesFired);
    }

    @Test
    public void filterProperties_BEDS_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 1,
                        0, 2147483647,
                        0, 0,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    @Test
    public void filterProperties_BATHROOMS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 1,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(1, rulesFired);
    }

    @Test
    public void filterProperties_BATHROOMS_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 0,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    @Test
    public void filterProperties_HEATING() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(2, rulesFired);
    }

    @Test
    public void filterProperties_HEATING_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.RADIANT),
                        Lists.emptyList(),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    @Test
    public void filterProperties_PETS() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.list(PetStatus.DOGS),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(1, rulesFired);
    }

    @Test
    public void filterProperties_PETS_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.list(PetStatus.IN_AQUARIUM),
                        Lists.emptyList())
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    @Test
    public void filterProperties_AMENITIES() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.list(Amenity.ELEVATOR))
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(2, rulesFired);
    }

    @Test
    public void filterProperties_AMENITIES_NOT_FOUND() {
        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        SmartSearch smartSearch = new SmartSearch(
                new PersonalInformation(0, 0, 0,
                        true, true, Lists.emptyList()),
                new PropertyInformation(0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        0, 2147483647,
                        Lists.list(Heating.FURNACE, Heating.BOILER),
                        Lists.emptyList(),
                        Lists.list(Amenity.SWIMMING_POOL))
        );
        ScoredProperties scoredProperties = new ScoredProperties(Lists.emptyList());

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();

        kieSession.insert(smartSearch);
        kieSession.insert(scoredProperties);
        kieSession.insert(p1);
        kieSession.insert(p2);

        int rulesFired = kieSession.fireAllRules();
        assertEquals(0, rulesFired);
    }

    private static class GetPropertiesForFiltering {
        private Property p1;
        private Property p2;

        Property getP1() {
            return p1;
        }

        Property getP2() {
            return p2;
        }

        GetPropertiesForFiltering invoke() {
            p1 = new Property();
            p1.setId(1L);
            p1.setPrice(500);
            p1.setSize(50);
            p1.setNumberOfBeds(2);
            p1.setNumberOfBathrooms(1);
            p1.setHeating(Heating.BOILER);
            p1.setStatus(PropertyStatus.FOR_SALE);
            p1.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS));
            p1.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.CABLE_READY));

            p2 = new Property();
            p2.setId(2L);
            p2.setPrice(5000);
            p2.setSize(500);
            p2.setNumberOfBeds(20);
            p2.setNumberOfBathrooms(10);
            p2.setHeating(Heating.FURNACE);
            p2.setStatus(PropertyStatus.FOR_SALE);
            p2.setAllowedPets(Set.of(PetStatus.CATS));
            p2.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.GATED, Amenity.SECURITY));
            return this;
        }
    }
}
