package rs.ac.uns.ftn.sbnz.models;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
import rs.ac.uns.ftn.sbnz.models.enums.PropertyStatus;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Coordinate coordinate;

    private int price;
    private int size;
    private int numberOfBeds;
    private int numberOfBathrooms;

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

    public Property() {
    }

}
