package rs.ac.uns.ftn.sbnz.cron;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.sbnz.service.AnalyticsService;

@Component
public class PropertyPriceAdvice {

    @Autowired
    private AnalyticsService service;

//    @Scheduled(fixedRate = 1000) // At every second.
    @Scheduled(fixedRate = 1209600) // Biweekly
    public void getPriceAdvice() {
        System.out.println(service.getPriceAdvice());
    }
}
