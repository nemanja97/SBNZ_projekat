package rs.ac.uns.ftn.sbnz.web.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.sbnz.models.drools.FinancialReport;
import rs.ac.uns.ftn.sbnz.service.ReportsService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping(value = "api/v1/reports")
@CrossOrigin
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<FinancialReport> getReport(
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return new ResponseEntity<>(
                reportsService.makeReportFor(
                        Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant())
                ),
                HttpStatus.OK
        );
    }
}
