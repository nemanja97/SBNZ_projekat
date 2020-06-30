package rs.ac.uns.ftn.sbnz.service;

import org.apache.maven.shared.invoker.MavenInvocationException;
import rs.ac.uns.ftn.sbnz.exception.RuleNotCompilingException;
import rs.ac.uns.ftn.sbnz.web.dto.v1.RuleDTO;

import java.io.IOException;
import java.util.List;

public interface RuleService {

    void modifyRule(String path, String content) throws IOException, MavenInvocationException, RuleNotCompilingException;

    List<RuleDTO> getRules();

    RuleDTO getRule(String path) throws IOException;

    void validate(String rule) throws RuleNotCompilingException;
}
