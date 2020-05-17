package rs.ac.uns.ftn.sbnz.service;

import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.ScoredProperties;
import rs.ac.uns.ftn.sbnz.web.dto.v1.SmartSearchDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyService {

    void addProperty(Property property);

    List<Property> getProperties();

    ScoredProperties getOptimalProperties(SmartSearchDTO smartSearchDTO);

    Optional<Property> getProperty(Long id);

    void updateProperty(Long id, Property property);

    void removeProperty(Long id);
}
