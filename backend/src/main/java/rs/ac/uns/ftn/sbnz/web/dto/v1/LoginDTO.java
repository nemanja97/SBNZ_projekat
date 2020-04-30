package rs.ac.uns.ftn.sbnz.web.dto.v1;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDTO {

    @Email(message = "Email must be in correct format")
    @NotBlank(message = "Email can't be blank")
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(@Email(message = "Email must be in correct format") @NotBlank(message = "Email can't be blank") String email,
                    @NotBlank(message = "Password can't be blank") String password) {
        this.email = email;
        this.password = password;
    }
}
