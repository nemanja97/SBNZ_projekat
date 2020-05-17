package rs.ac.uns.ftn.sbnz.web.dto.v1;

import rs.ac.uns.ftn.sbnz.models.enums.Interest;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public class PersonalInformationDTO {

	@PositiveOrZero
	private int youngerOccupants;
	@PositiveOrZero
	private int middleAgedOccupants;
	@PositiveOrZero
	private int olderOccupants;
	private Boolean expectingChildren;
	private Boolean hasVehicle;
	private List<Interest> interests;

	public PersonalInformationDTO() {
	}

	public PersonalInformationDTO(@PositiveOrZero int youngerOccupants, @PositiveOrZero int middleAgedOccupants,
								  @PositiveOrZero int olderOccupants, Boolean expectingChildren,
								  Boolean hasVehicle, List<Interest> interests) {
		this.youngerOccupants = youngerOccupants;
		this.middleAgedOccupants = middleAgedOccupants;
		this.olderOccupants = olderOccupants;
		this.expectingChildren = expectingChildren;
		this.hasVehicle = hasVehicle;
		this.interests = interests;
	}

	public int getYoungerOccupants() {
		return youngerOccupants;
	}

	public void setYoungerOccupants(int youngerOccupants) {
		this.youngerOccupants = youngerOccupants;
	}

	public int getMiddleAgedOccupants() {
		return middleAgedOccupants;
	}

	public void setMiddleAgedOccupants(int middleAgedOccupants) {
		this.middleAgedOccupants = middleAgedOccupants;
	}

	public int getOlderOccupants() {
		return olderOccupants;
	}

	public void setOlderOccupants(int olderOccupants) {
		this.olderOccupants = olderOccupants;
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
