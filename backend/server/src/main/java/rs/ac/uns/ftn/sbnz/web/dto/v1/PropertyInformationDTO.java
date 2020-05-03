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

    @PositiveOrZero(message = "Min price must be positive")
    private int priceLow;
    @PositiveOrZero(message = "Max price must be positive")
    private int priceHigh;

    @PositiveOrZero(message = "Max property size must be positive")
    private int sizeLow;
    @PositiveOrZero(message = "Max property size must be positive")
    private int sizeHigh;

    @PositiveOrZero(message = "Min number of beds must be positive")
    private int bedsLow;
    @PositiveOrZero(message = "Max number of beds must be positive")
    private int bedsHigh;

    @PositiveOrZero(message = "Max number of bathrooms must be positive")
    private int bathroomsLow;
    @PositiveOrZero(message = "Max number of bathrooms must be positive")
    private int bathroomsHigh;

    private List<Heating> heating;
    private List<PetStatus> pets;
    private List<Amenity> amenities;

    public PropertyInformationDTO() {
    }

    public PropertyInformationDTO(@PositiveOrZero(message = "Min price must be positive") int priceLow,
                                  @PositiveOrZero(message = "Max price must be positive") int priceHigh,
                                  @PositiveOrZero(message = "Max property size must be positive") int sizeLow,
                                  @PositiveOrZero(message = "Max property size must be positive") int sizeHigh,
                                  @PositiveOrZero(message = "Min number of beds must be positive") int bedsLow,
                                  @PositiveOrZero(message = "Max number of beds must be positive") int bedsHigh,
                                  @PositiveOrZero(message = "Max number of bathrooms must be positive") int bathroomsLow,
                                  @PositiveOrZero(message = "Max number of bathrooms must be positive") int bathroomsHigh,
                                  List<Heating> heating, List<PetStatus> pets, List<Amenity> amenities) {
        this.priceLow = priceLow;
        this.priceHigh = priceHigh;
        this.sizeLow = sizeLow;
        this.sizeHigh = sizeHigh;
        this.bedsLow = bedsLow;
        this.bedsHigh = bedsHigh;
        this.bathroomsLow = bathroomsLow;
        this.bathroomsHigh = bathroomsHigh;
        this.heating = heating;
        this.pets = pets;
        this.amenities = amenities;
    }
}
