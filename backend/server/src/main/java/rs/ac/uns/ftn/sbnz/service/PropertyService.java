package rs.ac.uns.ftn.sbnz.service;

import rs.ac.uns.ftn.sbnz.models.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    void addProperty(Property property);

    List<Property> getProperties();

    Optional<Property> getProperty(Long id);

    void updateProperty(Long id, Property property);

    void removeProperty(Long id);
}
