package rs.ac.uns.ftn.sbnz.exception;

public class PlaceOfInterestNotFoundException extends RuntimeException {

    public PlaceOfInterestNotFoundException() {
        super("Place of interest not found.");
    }
}
