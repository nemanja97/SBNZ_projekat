package rs.ac.uns.ftn.sbnz.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.exception.PropertyNotFoundException;
import rs.ac.uns.ftn.sbnz.models.*;
import rs.ac.uns.ftn.sbnz.models.drools.*;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.Interest;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.repository.PropertyRepository;
import rs.ac.uns.ftn.sbnz.service.PlaceOfInterestService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final KieContainer kieContainer;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PlaceOfInterestService placeOfInterestService;

    @Autowired
    public PropertyServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Override
    public void addProperty(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public ScoredProperties getOptimalProperties(SmartSearch smartSearch) {
        List<Property> properties = this.getProperties();
        List<PlaceOfInterest> placesOfInterest = placeOfInterestService.getPlacesOfInterest();
        ScoredProperties scoredProperties = new ScoredProperties(new ArrayList<>());

        KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        properties.forEach(kieSession::insert);
        placesOfInterest.forEach(kieSession::insert);
        kieSession.insert(scoredProperties);
        kieSession.insert(smartSearch);

        kieSession.getAgenda().getAgendaGroup("filtering").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("scaling").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("amenity_score_calculation").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("distance_score_calculation").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("heating_score_calculation").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("pet_score_calculation").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("finishing").setFocus();
        System.out.println(kieSession.fireAllRules());

        kieSession.dispose();

        scoredProperties.getPropertyWithScores().sort(PropertyWithScore::compareTo);
        scoredProperties.getPropertyWithScores().forEach(
                ps -> System.out.println(ps.getScore()));
        return scoredProperties;
    }

    @Override
    public Property recommend(Property property) {
        List<Property> properties = this.getProperties();
        property.setId(0L);

        List<Property> filteredProperties = new ArrayList<>();

        KieSession kieSession = kieContainer.getKieBase("KBase3").newKieSession();
        kieSession.getAgenda().getAgendaGroup("recommend").setFocus();

        kieSession.insert(filteredProperties);
        kieSession.insert(property);
        properties.forEach(kieSession::insert);

        System.out.println(kieSession.fireAllRules());
        propertyRepository.save(property);
        kieSession.dispose();

        return property;
    }

    @Override
    public Optional<Property> getProperty(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public void updateProperty(Long id, Property property) {
        Optional<Property> optionalProperty = this.getProperty(id);
        if (optionalProperty.isPresent()) {
            Property oldProperty = optionalProperty.get();
            oldProperty.setCoordinate(property.getCoordinate());
            oldProperty.setPrice(property.getPrice());
            oldProperty.setSize(property.getSize());
            oldProperty.setNumberOfBeds(property.getNumberOfBeds());
            oldProperty.setNumberOfBathrooms(property.getNumberOfBathrooms());
            oldProperty.setHeating(property.getHeating());
            oldProperty.setStatus(property.getStatus());
            oldProperty.setAllowedPets(property.getAllowedPets());
            oldProperty.setAmenities(property.getAmenities());
            oldProperty.setPriceRecommendation(property.getPriceRecommendation());
            propertyRepository.save(oldProperty);
        } else {
            throw new PropertyNotFoundException();
        }
    }

    @Override
    public void removeProperty(Long id) {
        Optional<Property> optionalProperty = this.getProperty(id);
        if (optionalProperty.isPresent()) {
            propertyRepository.delete(optionalProperty.get());
        } else {
            throw new PropertyNotFoundException();
        }
    }
}
