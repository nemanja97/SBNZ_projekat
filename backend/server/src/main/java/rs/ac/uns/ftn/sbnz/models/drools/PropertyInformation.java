package rs.ac.uns.ftn.sbnz.models.drools;

import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class PropertyInformation {

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

    public PropertyInformation() {
    }

    public PropertyInformation(@PositiveOrZero(message = "Min price must be positive") int priceLow,
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

	public int getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(int priceLow) {
		this.priceLow = priceLow;
	}

	public int getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(int priceHigh) {
		this.priceHigh = priceHigh;
	}

	public int getSizeLow() {
		return sizeLow;
	}

	public void setSizeLow(int sizeLow) {
		this.sizeLow = sizeLow;
	}

	public int getSizeHigh() {
		return sizeHigh;
	}

	public void setSizeHigh(int sizeHigh) {
		this.sizeHigh = sizeHigh;
	}

	public int getBedsLow() {
		return bedsLow;
	}

	public void setBedsLow(int bedsLow) {
		this.bedsLow = bedsLow;
	}

	public int getBedsHigh() {
		return bedsHigh;
	}

	public void setBedsHigh(int bedsHigh) {
		this.bedsHigh = bedsHigh;
	}

	public int getBathroomsLow() {
		return bathroomsLow;
	}

	public void setBathroomsLow(int bathroomsLow) {
		this.bathroomsLow = bathroomsLow;
	}

	public int getBathroomsHigh() {
		return bathroomsHigh;
	}

	public void setBathroomsHigh(int bathroomsHigh) {
		this.bathroomsHigh = bathroomsHigh;
	}

	public List<Heating> getHeating() {
		return heating;
	}

	public void setHeating(List<Heating> heating) {
		this.heating = heating;
	}

	public List<PetStatus> getPets() {
		return pets;
	}

	public void setPets(List<PetStatus> pets) {
		this.pets = pets;
	}

	public List<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<Amenity> amenities) {
		this.amenities = amenities;
	}
    
}
