package rs.ac.uns.ftn.sbnz.models.drools;

import rs.ac.uns.ftn.sbnz.models.Property;

import java.util.List;

public class PropertyList {

    private List<Property> properties;

    public PropertyList(List<Property> properties) {
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
