package rs.ac.uns.ftn.sbnz.service.implementation;

import org.apache.commons.io.FileUtils;
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
        Files.writeString(Paths.get(path), content);
        invoker.execute(request);
    }

    @Override
    public List<RuleDTO> getRules() {
        List<RuleDTO> ruleDTOList = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get("../drools-spring-kjar/src/main/resources/rs.ac.uns.ftn.sbnz/rules"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(p -> {
                        try {
                            ruleDTOList.add(getRule(p.toString()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ruleDTOList;
    }

    @Override
    public RuleDTO getRule(String path) throws IOException {
        return new RuleDTO(path, Files.readString(Path.of(path)));
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
