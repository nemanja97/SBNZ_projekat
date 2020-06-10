package rs.ac.uns.ftn.sbnz.web.controllers.v1;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbnz.service.RuleService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.RuleDTO;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/rules")
@CrossOrigin
public class RulesController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> modifyRule(@Valid @RequestBody RuleDTO ruleDTO) {
        try {
            ruleService.modifyRule(ruleDTO.getPath(), ruleDTO.getContent());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("File IO error", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Maven error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    //    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<RuleDTO>> getRules() {
        return new ResponseEntity<>(ruleService.getRules(), HttpStatus.OK);
    }
}
