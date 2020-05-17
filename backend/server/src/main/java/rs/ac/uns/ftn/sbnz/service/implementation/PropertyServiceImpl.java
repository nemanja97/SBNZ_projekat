package rs.ac.uns.ftn.sbnz.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.exception.PropertyNotFoundException;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.Interest;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.repository.PropertyRepository;
import rs.ac.uns.ftn.sbnz.service.PlaceOfInterestService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.PersonalInformationDTO;
import rs.ac.uns.ftn.sbnz.web.dto.v1.PropertyInformationDTO;
import rs.ac.uns.ftn.sbnz.web.dto.v1.SmartSearchDTO;

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
    public ScoredProperties getOptimalProperties(SmartSearchDTO smartSearchDTO) {
        List<Property> properties = this.getProperties();
        List<PlaceOfInterest> placesOfInterest = placeOfInterestService.getPlacesOfInterest();
        ScoredProperties scoredProperties = new ScoredProperties(new ArrayList<>());

        smartSearchDTO = new SmartSearchDTO(
                new PersonalInformationDTO(
                        2, 3, 4,
                        true, true,
                        Arrays.asList(Interest.CULTURE, Interest.FOOD)),
                new PropertyInformationDTO(
                        0, 5000000,
                        30, 9000,
                        0, 20,
                        0, 20,
                        Arrays.asList(Heating.BOILER, Heating.DUCTLESS_MINI_SPLITS, Heating.RADIANT),
                        Arrays.asList(PetStatus.CATS),
                        Arrays.asList(Amenity.ELEVATOR)
                ));

        KieSession kieSession = kieContainer.newKieSession();
        properties.forEach(kieSession::insert);
        placesOfInterest.forEach(kieSession::insert);
        kieSession.insert(scoredProperties);
        kieSession.insert(smartSearchDTO);

        System.out.println(kieSession.fireAllRules());
        kieSession.dispose();

        scoredProperties.getPropertyWithScores().sort(PropertyWithScore::compareTo);
        scoredProperties.getPropertyWithScores().forEach(
                ps -> System.out.println(ps.getScore()));
        return scoredProperties;
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
