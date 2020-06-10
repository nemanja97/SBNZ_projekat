package rs.ac.uns.ftn.sbnz.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.events.MoreInfoClickedEvent;
import rs.ac.uns.ftn.sbnz.repository.PropertyRepository;
import rs.ac.uns.ftn.sbnz.service.AnalyticsService;
import rs.ac.uns.ftn.sbnz.service.PropertyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final KieContainer kieContainer;

    private KieSession analyticsSession;

    private final PropertyService propertyService;

    private final PropertyRepository propertyRepository;


    public AnalyticsServiceImpl(KieContainer kieContainer, PropertyService propertyService, PropertyRepository repository) {
        this.kieContainer = kieContainer;
        this.analyticsSession = this.kieContainer.getKieBase("KBase1").newKieSession();
        this.propertyService = propertyService;
        this.propertyRepository = repository;
    }

    @Override
    public void addMoreInfoEventOnPropertyClick(Long id) {
        MoreInfoClickedEvent event = new MoreInfoClickedEvent(new Date(), id);
        analyticsSession.insert(event);
        System.out.println(String.format("NUM OF FACTS %s", analyticsSession.getFactCount()));
    }

    @Override
    public int getPriceAdvice() {
        List<FactHandle> factHandles = new ArrayList<>();
        List<Property> properties = propertyService.getProperties();
        properties.forEach(p -> factHandles.add(analyticsSession.insert(p)));

        int fired = analyticsSession.fireAllRules();

        properties.forEach(propertyRepository::save);
        factHandles.forEach(fh -> analyticsSession.delete(fh));
        return fired;
    }

}
