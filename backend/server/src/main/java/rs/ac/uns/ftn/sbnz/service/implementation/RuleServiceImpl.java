package rs.ac.uns.ftn.sbnz.service.implementation;

import org.apache.maven.shared.invoker.*;
import org.apache.tools.ant.filters.StringInputStream;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.exception.RuleNotCompilingException;
import rs.ac.uns.ftn.sbnz.service.RuleService;
import rs.ac.uns.ftn.sbnz.web.dto.v1.RuleDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class RuleServiceImpl implements RuleService {

    private InvocationRequest request;
    private Invoker invoker;
    private final String rulesPath = "../drools-spring-kjar/src/main/resources/rs.ac.uns.ftn.sbnz/rules";

    @Autowired
    public RuleServiceImpl(@Value("${mvn.home}") String mavenHome) {
        request = new DefaultInvocationRequest();
        request.setPomFile( new File( "../drools-spring-kjar/pom.xml" ) );
        request.setGoals( Arrays.asList( "clean", "install" ) );

        invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(mavenHome));
    }

    @Override
    public void modifyRule(String path, String content) throws IOException, MavenInvocationException, RuleNotCompilingException {
        validate(content);
        Files.writeString(Paths.get(String.format("%s/%s", rulesPath, path)), content);
        invoker.execute(request);
    }

    @Override
    public List<RuleDTO> getRules() {
        List<RuleDTO> ruleDTOList = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(rulesPath))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(p -> {
                        try {
                            ruleDTOList.add(getRuleFullPath(p.toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleDTOList;
    }

    public RuleDTO getRuleFullPath(String path) throws IOException {
        return new RuleDTO(path.substring(path.indexOf("rules") + "rules".length() + 1), Files.readString(Path.of(path)));
    }

    @Override
    public RuleDTO getRule(String path) throws IOException {
        return new RuleDTO(path, Files.readString(Path.of(String.format("%s/%s", rulesPath, path))));
    }

    @Override
    public void removeRule(String path) throws IOException, MavenInvocationException {
        Files.delete(Path.of(String.format("%s/%s", rulesPath, path)));
        invoker.execute(request);
    }

    @Override
    public void validate(String rule) throws RuleNotCompilingException {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addResource(ResourceFactory.newInputStreamResource(new StringInputStream(rule)), ResourceType.DRL);

        KieBaseConfiguration kieBaseConfiguration = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
        kieBaseConfiguration.setOption(EqualityBehaviorOption.EQUALITY);

        try {
            kieHelper.build(kieBaseConfiguration);
        } catch (Exception ignored) {
        } finally {
            Results results = kieHelper.verify();
            if (results.hasMessages(Message.Level.ERROR)) {
                throw new RuleNotCompilingException(results.getMessages(Message.Level.ERROR));
            }
        }
    }
}
