package rs.ac.uns.ftn.sbnz.web.dto.v1;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SmartSearchDTO {

    @Valid
    private PersonalInformationDTO personalInformation;

    @NotNull(message = "Property information can't be empty")
    @Valid
    private PropertyInformationDTO propertyInformation;

    public SmartSearchDTO() {
    }

    public SmartSearchDTO(@Valid PersonalInformationDTO personalInformation,
                          @NotNull(message = "Property information can't be empty")
                          @Valid PropertyInformationDTO propertyInformation) {
        this.personalInformation = personalInformation;
        this.propertyInformation = propertyInformation;
    }

	public PersonalInformationDTO getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(PersonalInformationDTO personalInformation) {
		this.personalInformation = personalInformation;
	}

	public PropertyInformationDTO getPropertyInformation() {
		return propertyInformation;
	}

	public void setPropertyInformation(PropertyInformationDTO propertyInformation) {
		this.propertyInformation = propertyInformation;
	}
    
}
