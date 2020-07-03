package rs.ac.uns.ftn.sbnz.service;

import rs.ac.uns.ftn.sbnz.models.drools.FinancialReport;

import java.util.Date;

public interface ReportsService {

    FinancialReport makeReportFor(Date from, Date to);
}
