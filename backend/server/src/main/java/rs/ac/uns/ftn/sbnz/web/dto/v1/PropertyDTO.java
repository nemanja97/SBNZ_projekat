package rs.ac.uns.ftn.sbnz.web.dto.v1;

import rs.ac.uns.ftn.sbnz.models.*;
import rs.ac.uns.ftn.sbnz.models.enums.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

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

    private PriceRecommendation priceRecommendation;

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
                       Heating heating, PropertyStatus status, Set<PetStatus> allowedPets, Set<Amenity> amenities,
                       PriceRecommendation priceRecommendation) {
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
        this.priceRecommendation = priceRecommendation;
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
        this.priceRecommendation = property.getPriceRecommendation();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public Heating getHeating() {
		return heating;
	}

	public void setHeating(Heating heating) {
		this.heating = heating;
	}

	public PropertyStatus getStatus() {
		return status;
	}

	public void setStatus(PropertyStatus status) {
		this.status = status;
	}

	public Set<PetStatus> getAllowedPets() {
		return allowedPets;
	}

	public void setAllowedPets(Set<PetStatus> allowedPets) {
		this.allowedPets = allowedPets;
	}

	public Set<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenity> amenities) {
		this.amenities = amenities;
	}

    public PriceRecommendation getPriceRecommendation() {
        return priceRecommendation;
    }

    public void setPriceRecommendation(PriceRecommendation priceRecommendation) {
        this.priceRecommendation = priceRecommendation;
    }
}
