package rs.ac.uns.ftn.sbnz.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.models.drools.events.MoreInfoClickedEvent;
import rs.ac.uns.ftn.sbnz.service.AnalyticsService;

import java.util.Date;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final KieContainer kieContainer;

    private KieSession analyticsSession;

    public AnalyticsServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        this.analyticsSession = this.kieContainer.newKieSession();
    }

    @Override
    public void addMoreInfoEventOnPropertyClick(Long id) {
        MoreInfoClickedEvent event = new MoreInfoClickedEvent(new Date(), id);
        analyticsSession.insert(event);
    }

    @Override
    public int getMoreInfoEventsForProperty(Long id) {
        return 0;
    }

}
