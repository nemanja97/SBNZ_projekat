package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.*;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.models.enums.PropertyStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Getter
@Setter
public class PropertyDTO {

    private Long id;

    @NotNull(message = "Must have coordinates")
    private Coordinate coordinate;

    @PositiveOrZero(message = "Price must be positive")
    private int price;

    @PositiveOrZero(message = "Size must be positive")
    private int size;

    @PositiveOrZero(message = "Number of beds must be positive")
    private int numberOfBeds;

    @PositiveOrZero(message = "Number of bathrooms must be positive")
    private int numberOfBathrooms;

    private Heating heating;

    private PropertyStatus status;

    private Set<PetStatus> allowedPets;

    private Set<Amenity> amenities;

    public PropertyDTO() {
    }

    public PropertyDTO(@NotNull(message = "Must have coordinates") Coordinate coordinate,
                       @PositiveOrZero(message = "Price must be positive") int price,
                       @PositiveOrZero(message = "Size must be positive") int size,
                       @PositiveOrZero(message = "Number of beds must be positive") int numberOfBeds,
                       @PositiveOrZero(message = "Number of bathrooms must be positive") int numberOfBathrooms,
                       Heating heating, Set<PetStatus> allowedPets, Set<Amenity> amenities) {
        this.coordinate = coordinate;
        this.price = price;
        this.size = size;
        this.numberOfBeds = numberOfBeds;
        this.numberOfBathrooms = numberOfBathrooms;
        this.heating = heating;
        this.allowedPets = allowedPets;
        this.amenities = amenities;
        this.status = PropertyStatus.FOR_SALE;
    }

    public PropertyDTO(Long id,
                       @NotNull(message = "Must have coordinates") Coordinate coordinate,
                       @PositiveOrZero(message = "Price must be positive") int price,
                       @PositiveOrZero(message = "Size must be positive") int size,
                       @PositiveOrZero(message = "Number of beds must be positive") int numberOfBeds,
                       @PositiveOrZero(message = "Number of bathrooms must be positive") int numberOfBathrooms,
                       Heating heating, PropertyStatus status, Set<PetStatus> allowedPets, Set<Amenity> amenities) {
        this.id = id;
        this.coordinate = coordinate;
        this.price = price;
        this.size = size;
        this.numberOfBeds = numberOfBeds;
        this.numberOfBathrooms = numberOfBathrooms;
        this.heating = heating;
        this.status = status;
        this.allowedPets = allowedPets;
        this.amenities = amenities;
    }

    public PropertyDTO(Property property) {
        this.id = property.getId();
        this.coordinate = property.getCoordinate();
        this.price = property.getPrice();
        this.size = property.getSize();
        this.numberOfBeds = property.getNumberOfBeds();
        this.numberOfBathrooms = property.getNumberOfBathrooms();
        this.heating = property.getHeating();
        this.status = property.getStatus();
        this.allowedPets = property.getAllowedPets();
        this.amenities = property.getAmenities();
    }

    public Property convertToEntity() {
        Property property = new Property();
        property.setId(this.id);
        property.setCoordinate(this.coordinate);
        property.setPrice(this.price);
        property.setSize(this.size);
        property.setNumberOfBeds(this.numberOfBeds);
        property.setNumberOfBathrooms(this.numberOfBathrooms);
        property.setHeating(this.heating);
        property.setStatus(this.status);
        property.setAllowedPets(this.allowedPets);
        property.setAmenities(this.amenities);
        return property;
    }
}
