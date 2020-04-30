package rs.ac.uns.ftn.sbnz.service;

import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;

import java.util.List;
import java.util.Optional;

public interface PlaceOfInterestService {

    void addPlaceOfInterest(PlaceOfInterest placeOfInterest);

    List<PlaceOfInterest> getPlacesOfInterest();

    Optional<PlaceOfInterest> getPlaceOfInterest(Long id);

    void updatePlaceOfInterest(Long id, PlaceOfInterest placeOfInterest);

    void removePlaceOfInterest(Long id);
}
