package rs.ac.uns.ftn.sbnz.service;

import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.SmartSearch;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.models.enums.PropertyStatus;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    void addProperty(Property property);

    List<Property> getProperties();

    List<Property> getProperties(PropertyStatus status);

    ScoredProperties getOptimalProperties(SmartSearch smartSearch);

    Property recommend(Property property);

    Optional<Property> getProperty(Long id);

    void updateProperty(Long id, Property property);

    void removeProperty(Long id);
}
