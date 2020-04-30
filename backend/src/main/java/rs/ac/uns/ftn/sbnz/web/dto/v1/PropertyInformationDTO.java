package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
public class PropertyInformationDTO {

    @PositiveOrZero(message = "Price must be positive")
    private int priceLow;
    @PositiveOrZero(message = "Price must be positive")
    private int priceHigh;

    @PositiveOrZero(message = "Property size must be positive")
    private int propertySizeLow;
    @PositiveOrZero(message = "Property size must be positive")
    private int propertySizeHigh;

    @PositiveOrZero(message = "Number of beds must be positive")
    private int numberOfBeds;

    @PositiveOrZero(message = "Number of bathrooms must be positive")
    private int numberOfBathrooms;

    private Heating heating;
    private List<PetStatus> allowedPets;
    private List<Amenity> amenities;

    public PropertyInformationDTO() {
    }

    public PropertyInformationDTO(
            @PositiveOrZero(message = "Price must be positive") int priceLow,
            @PositiveOrZero(message = "Price must be positive") int priceHigh,
            @PositiveOrZero(message = "Property size must be positive") int propertySizeLow,
            @PositiveOrZero(message = "Property size must be positive") int propertySizeHigh,
            @PositiveOrZero(message = "Number of beds must be positive") int numberOfBeds,
            @PositiveOrZero(message = "Number of bathrooms must be positive") int numberOfBathrooms,
            Heating heating, List<PetStatus> allowedPets, List<Amenity> amenities) {
        this.priceLow = priceLow;
        this.priceHigh = priceHigh;
        this.propertySizeLow = propertySizeLow;
        this.propertySizeHigh = propertySizeHigh;
        this.numberOfBeds = numberOfBeds;
        this.numberOfBathrooms = numberOfBathrooms;
        this.heating = heating;
        this.allowedPets = allowedPets;
        this.amenities = amenities;
    }
}
