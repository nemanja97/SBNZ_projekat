package rs.ac.uns.ftn.sbnz.web.dto.v1;

import rs.ac.uns.ftn.sbnz.models.enums.Interest;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

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

	public List<Integer> getOccupantAges() {
		return occupantAges;
	}

	public void setOccupantAges(List<Integer> occupantAges) {
		this.occupantAges = occupantAges;
	}

	public Boolean getExpectingChildren() {
		return expectingChildren;
	}

	public void setExpectingChildren(Boolean expectingChildren) {
		this.expectingChildren = expectingChildren;
	}

	public Boolean getHasVehicle() {
		return hasVehicle;
	}

	public void setHasVehicle(Boolean hasVehicle) {
		this.hasVehicle = hasVehicle;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
    
}
