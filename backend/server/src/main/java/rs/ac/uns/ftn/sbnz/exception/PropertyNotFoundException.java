package rs.ac.uns.ftn.sbnz.exception;

public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException() {
        super("Property not found.");
    }
}
