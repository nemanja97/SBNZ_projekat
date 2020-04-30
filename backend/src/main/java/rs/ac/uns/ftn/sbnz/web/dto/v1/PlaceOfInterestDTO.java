package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.Coordinate;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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
}
