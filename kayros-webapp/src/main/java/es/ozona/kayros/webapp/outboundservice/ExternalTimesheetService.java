package es.ozona.kayros.webapp.outboundservice;

import es.ozona.kayros.webapp.shareddomain.model.Timesheet;

public interface ExternalTimesheetService {

	Timesheet clock(String username);
}
