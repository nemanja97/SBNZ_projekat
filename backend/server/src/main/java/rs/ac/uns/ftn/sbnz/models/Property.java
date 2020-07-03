package rs.ac.uns.ftn.sbnz.models;

import org.kie.api.definition.type.Position;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import rs.ac.uns.ftn.sbnz.models.enums.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Position(0)
    private Long id;

    @Embedded
    private Coordinate coordinate;

    private int price;

    private int size;

    private int numberOfBeds;

    private int numberOfBathrooms;

	@Position(1)
    @Enumerated(EnumType.STRING)
    private Heating heating;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass = PetStatus.class)
    @Enumerated(EnumType.STRING)
    private Set<PetStatus> allowedPets;

    @Column
    @ElementCollection(fetch = FetchType.EAGER, targetClass = Amenity.class)
    @Enumerated(EnumType.STRING)
    private Set<Amenity> amenities;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<MultimediaFile> multimedia;

	@CreatedDate
	private Date creationDate;

	@LastModifiedDate
	private Date modifiedDate;

	@Enumerated(EnumType.STRING)
	private PriceRecommendation priceRecommendation;

    public Property() {
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

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
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

	public Set<MultimediaFile> getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(Set<MultimediaFile> multimedia) {
		this.multimedia = multimedia;
	}

	public PriceRecommendation getPriceRecommendation() {
		return priceRecommendation;
	}

	public void setPriceRecommendation(PriceRecommendation priceRecommendation) {
		this.priceRecommendation = priceRecommendation;
	}

	public double calculateDistance(Property other) {
    	return this.coordinate.calculateDistance(other.getCoordinate());
	}

	public double calculateDistance(PlaceOfInterest other) {
    	return this.coordinate.calculateDistance(other.getCoordinate());
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Property property = (Property) o;
		return price == property.price &&
				size == property.size &&
				numberOfBeds == property.numberOfBeds &&
				numberOfBathrooms == property.numberOfBathrooms &&
				Objects.equals(id, property.id) &&
				Objects.equals(coordinate, property.coordinate) &&
				heating == property.heating &&
				status == property.status &&
				Objects.equals(allowedPets, property.allowedPets) &&
				Objects.equals(amenities, property.amenities) &&
				Objects.equals(multimedia, property.multimedia);
	}

	@Override
	public String toString() {
		return "Property{" +
				"id=" + id +
				", coordinate=" + coordinate +
				", price=" + price +
				", size=" + size +
				", numberOfBeds=" + numberOfBeds +
				", numberOfBathrooms=" + numberOfBathrooms +
				", heating=" + heating +
				", status=" + status +
				", allowedPets=" + allowedPets +
				", amenities=" + amenities +
				", multimedia=" + multimedia +
				", creationDate=" + creationDate +
				", modifiedDate=" + modifiedDate +
				", priceRecommendation=" + priceRecommendation +
				'}';
	}
}
