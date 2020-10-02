package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.List;

import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

public interface ExternalTimesheetService {

	TimesheetResource clock(String username);

	List<WorkingTimePeriod> searchCurrentByEmployeeId(String employeeId);
}
