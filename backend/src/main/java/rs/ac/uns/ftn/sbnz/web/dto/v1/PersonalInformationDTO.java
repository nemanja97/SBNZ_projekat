package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.sbnz.models.enums.Interest;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
public class PersonalInformationDTO {

    private List<@PositiveOrZero(message = "Ages must be positive numbers") Integer> occupantAges;
    private Boolean expectingChildren;
    private Boolean hasVehicle;
    private List<Interest> interests;

    public PersonalInformationDTO() {
    }

    public PersonalInformationDTO(List<@PositiveOrZero(message = "Ages must be positive numbers") Integer> occupantAges,
                                  Boolean expectingChildren, Boolean hasVehicle, List<Interest> interests) {
        this.occupantAges = occupantAges;
        this.expectingChildren = expectingChildren;
        this.hasVehicle = hasVehicle;
        this.interests = interests;
    }
}
