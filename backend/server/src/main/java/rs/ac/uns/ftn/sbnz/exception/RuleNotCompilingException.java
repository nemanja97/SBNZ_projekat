package rs.ac.uns.ftn.sbnz.exception;

import lombok.Getter;
import lombok.Setter;
import org.kie.api.builder.Message;

import java.util.List;

@Getter
@Setter
public class RuleNotCompilingException extends Exception {

    List<Message> reason;

    public RuleNotCompilingException(List<Message> reasonBuildFailed) {
        super("Rule build failed");
        reason = reasonBuildFailed;
    }
}
