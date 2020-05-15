package rs.ac.uns.ftn.sbnz.web.dto.v1;

import rs.ac.uns.ftn.sbnz.models.Coordinate;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;

import javax.validation.constraints.NotNull;

public class PlaceOfInterestDTO {

    private Long id;

    @NotNull(message = "Must have coordinates")
    private Coordinate coordinate;

    @NotNull(message = "Must have type")
    private TypeOfPlace typeOfPlace;

    public PlaceOfInterestDTO() {
    }

    public PlaceOfInterestDTO(Long id,
                              @NotNull(message = "Must have coordinates") Coordinate coordinate,
                              @NotNull(message = "Must have type") TypeOfPlace typeOfPlace) {
        this.id = id;
        this.coordinate = coordinate;
        this.typeOfPlace = typeOfPlace;
    }

    public PlaceOfInterestDTO(PlaceOfInterest placeOfInterest) {
        this.id = placeOfInterest.getId();
        this.coordinate = placeOfInterest.getCoordinate();
        this.typeOfPlace = placeOfInterest.getTypeOfPlace();
    }

    public PlaceOfInterest convertToEntity() {
        PlaceOfInterest placeOfInterest = new PlaceOfInterest();
        placeOfInterest.setId(this.id);
        placeOfInterest.setCoordinate(this.coordinate);
        placeOfInterest.setTypeOfPlace(this.typeOfPlace);
        return placeOfInterest;
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

	public TypeOfPlace getTypeOfPlace() {
		return typeOfPlace;
	}

	public void setTypeOfPlace(TypeOfPlace typeOfPlace) {
		this.typeOfPlace = typeOfPlace;
	}
    
}
