package rs.ac.uns.ftn.sbnz.models;

import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;

import javax.persistence.*;

@Entity
public class PlaceOfInterest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    private TypeOfPlace typeOfPlace;

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
