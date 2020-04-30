package rs.ac.uns.ftn.sbnz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.exception.PropertyNotFoundException;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.repository.PropertyRepository;
import rs.ac.uns.ftn.sbnz.service.PropertyService;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public void addProperty(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public List<Property> getProperties() {
        return propertyRepository.findAll();
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
