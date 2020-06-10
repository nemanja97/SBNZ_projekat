package rs.ac.uns.ftn.sbnz.drools.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.models.enums.PropertyStatus;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class OptimalPrice {

    private static KieContainer kieContainer;

    private static final String agenda = "recommend";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("rs.ac.uns.ftn", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void optimalPriceTest() {
        KieSession kieSession = kieContainer.getKieBase("KBase3").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property original = getPropertiesForFiltering.getOriginal();
        Property p1 = getPropertiesForFiltering.getP1();
        Property p2 = getPropertiesForFiltering.getP2();
        Property p3 = getPropertiesForFiltering.getP3();

        kieSession.insert(new ArrayList<Property>());
        kieSession.insert(original);
        kieSession.insert(p1);
        kieSession.insert(p2);
        kieSession.insert(p3);

        assertEquals(0, original.getPrice());
        int rulesFired = kieSession.fireAllRules();
        assertEquals((p1.getPrice() + p2.getPrice()) / 2, original.getPrice());
    }

    @Test
    public void optimalPriceTest_NO_SIMILARITIES() {
        KieSession kieSession = kieContainer.getKieBase("KBase3").newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        GetPropertiesForFiltering getPropertiesForFiltering = new GetPropertiesForFiltering().invoke();
        Property original = getPropertiesForFiltering.getOriginal();
        Property p3 = getPropertiesForFiltering.getP3();

        kieSession.insert(new ArrayList<Property>());
        kieSession.insert(original);
        kieSession.insert(p3);

        int oldPrice = original.getPrice();
        assertEquals(0, original.getPrice());

        int rulesFired = kieSession.fireAllRules();
        assertEquals(oldPrice, original.getPrice());
    }

    private static class GetPropertiesForFiltering {
        private Property original;
        private Property p1;
        private Property p2;
        private Property p3;

        Property getOriginal() { return original; }

        Property getP1() {
            return p1;
        }

        Property getP2() {
            return p2;
        }

        Property getP3() {
            return p3;
        }

        GetPropertiesForFiltering invoke() {
            original = new Property();
            original.setId(0L);
            original.setPrice(0);
            original.setSize(50);
            original.setNumberOfBeds(2);
            original.setNumberOfBathrooms(1);
            original.setHeating(Heating.BOILER);
            original.setStatus(PropertyStatus.FOR_SALE);
            original.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS));
            original.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.CABLE_READY));

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
            p2.setPrice(1000);
            p2.setSize(55);
            p2.setNumberOfBeds(2);
            p2.setNumberOfBathrooms(1);
            p2.setHeating(Heating.BOILER);
            p2.setStatus(PropertyStatus.FOR_SALE);
            p2.setAllowedPets(Set.of(PetStatus.CATS, PetStatus.DOGS));
            p2.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.HIGH_SPEED_INTERNET_ACCESS, Amenity.CABLE_READY));

            p3 = new Property();
            p3.setId(3L);
            p3.setPrice(5000);
            p3.setSize(500);
            p3.setNumberOfBeds(20);
            p3.setNumberOfBathrooms(10);
            p3.setHeating(Heating.FURNACE);
            p3.setStatus(PropertyStatus.FOR_SALE);
            p3.setAllowedPets(Set.of(PetStatus.CATS));
            p3.setAmenities(Set.of(Amenity.ELEVATOR, Amenity.GATED, Amenity.SECURITY));
            return this;
        }
    }
}
