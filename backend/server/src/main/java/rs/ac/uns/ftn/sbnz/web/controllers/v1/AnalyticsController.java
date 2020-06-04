package rs.ac.uns.ftn.sbnz.web.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbnz.service.AnalyticsService;

@RestController
@RequestMapping(value = "api/v1/analytics")
@CrossOrigin
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @RequestMapping(value = "/property/moreInfo/{id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> clickedPropertyMoreInfo(@PathVariable Long id) {
        analyticsService.addMoreInfoEventOnPropertyClick(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/property/moreInfo/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPropertyMoreInfoAnalytics(@PathVariable Long id) {
        return new ResponseEntity<>(analyticsService.getMoreInfoEventsForProperty(id), HttpStatus.OK);
    }

}
