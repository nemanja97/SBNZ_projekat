package rs.ac.uns.ftn.sbnz.models;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PlaceOfInterest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(EnumType.STRING)
    private TypeOfPlace typeOfPlace;
}
