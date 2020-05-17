package rs.ac.uns.ftn.sbnz.models.drools;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SmartSearch {

    @Valid
    private PersonalInformation personalInformation;

    @NotNull(message = "Property information can't be empty")
    @Valid
    private PropertyInformation propertyInformation;

    public SmartSearch() {
    }

    public SmartSearch(@Valid PersonalInformation personalInformation,
                       @NotNull(message = "Property information can't be empty")
                          @Valid PropertyInformation propertyInformation) {
        this.personalInformation = personalInformation;
        this.propertyInformation = propertyInformation;
    }

	public PersonalInformation getPersonalInformation() {
		return personalInformation;
	}

	public void setPersonalInformation(PersonalInformation personalInformation) {
		this.personalInformation = personalInformation;
	}

	public PropertyInformation getPropertyInformation() {
		return propertyInformation;
	}

	public void setPropertyInformation(PropertyInformation propertyInformation) {
		this.propertyInformation = propertyInformation;
	}
    
}
