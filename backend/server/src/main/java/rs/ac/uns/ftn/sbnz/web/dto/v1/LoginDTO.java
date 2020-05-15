package rs.ac.uns.ftn.sbnz.web.dto.v1;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
