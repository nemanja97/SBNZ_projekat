package rs.ac.uns.ftn.sbnz.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.exception.PropertyNotFoundException;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.PlaceOfInterestList;
import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.repository.PropertyRepository;
import rs.ac.uns.ftn.sbnz.service.PlaceOfInterestService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.SmartSearchDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        ScoredProperties scoredProperties = new ScoredProperties(
                this.getProperties().stream().map(
                        property -> new PropertyWithScore(property, 0)).collect(Collectors.toList())
        );
//        PlaceOfInterestList placeOfInterestList = new PlaceOfInterestList(placeOfInterestService.getPlacesOfInterest());

        KieSession kieSession = kieContainer.newKieSession("ProjectSession");

        kieSession.insert(scoredProperties.getPropertyWithScores().get(0).getProperty());

//        scoredProperties.getPropertyWithScores().forEach(kieSession::insert);
//        kieSession.insert(poi);
        System.out.println(kieSession.fireAllRules());
        kieSession.dispose();

        scoredProperties.getPropertyWithScores().sort(PropertyWithScore::compareTo);
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
