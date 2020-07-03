package rs.ac.uns.ftn.sbnz.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.models.Property;
import rs.ac.uns.ftn.sbnz.models.drools.FinancialReport;
import rs.ac.uns.ftn.sbnz.service.PropertyService;
import rs.ac.uns.ftn.sbnz.service.ReportsService;

import java.util.Date;
import java.util.List;

@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    PropertyService propertyService;

    private final KieContainer kieContainer;

    @Autowired
    public ReportsServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Override
    public FinancialReport makeReportFor(Date from, Date to) {
        List<Property> properties = propertyService.getProperties();
        FinancialReport report = new FinancialReport(from, to);

        KieSession kieSession = kieContainer.getKieBase("KBase4").newKieSession();
        properties.forEach(kieSession::insert);
        kieSession.insert(report);

        kieSession.getAgenda().getAgendaGroup("reports").setFocus();
        System.out.println(kieSession.fireAllRules());

        return report;
    }
}
