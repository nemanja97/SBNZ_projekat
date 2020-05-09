package rs.ac.uns.ftn.sbnz.web.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbnz.models.User;
import rs.ac.uns.ftn.sbnz.security.TokenUtils;
import rs.ac.uns.ftn.sbnz.service.UserService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.LoginDTO;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @Validated
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@Valid @RequestBody LoginDTO login){
        try {
            String token = tryToAuthenticate(login);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Login failed; Invalid user email or password", HttpStatus.BAD_REQUEST);
        }
    }

    private String tryToAuthenticate(LoginDTO loginInfo) {
        User user = userService.findUserByEmail(loginInfo.getEmail());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getId(), loginInfo.getPassword());
        authenticationManager.authenticate(token);
        UserDetails details = userDetailsService.loadUserByUsername(user.getId().toString());
        return tokenUtils.generateToken(details, user.getRole() );
    }
}
