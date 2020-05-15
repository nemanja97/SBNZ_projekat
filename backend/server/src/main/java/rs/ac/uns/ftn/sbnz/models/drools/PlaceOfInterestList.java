package rs.ac.uns.ftn.sbnz.models.drools;

import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;

import java.util.List;

public class PlaceOfInterestList {

    private List<PlaceOfInterest> placesOfInterests;

    public PlaceOfInterestList(List<PlaceOfInterest> placesOfInterests) {
        this.placesOfInterests = placesOfInterests;
    }

    public List<PlaceOfInterest> getPlacesOfInterests() {
        return placesOfInterests;
    }

    public void setPlacesOfInterests(List<PlaceOfInterest> placesOfInterests) {
        this.placesOfInterests = placesOfInterests;
    }
}
