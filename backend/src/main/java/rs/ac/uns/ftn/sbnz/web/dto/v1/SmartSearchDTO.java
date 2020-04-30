package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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
}
