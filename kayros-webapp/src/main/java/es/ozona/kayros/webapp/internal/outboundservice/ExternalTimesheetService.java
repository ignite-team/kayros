package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.List;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

public interface ExternalTimesheetService {

	TimesheetResource clock(Employee employee);

	List<WorkingTimePeriod> searchCurrentByEmployeeId(String employeeId);

	List<WorkingTimePeriod> searchTimesheetsByEmployeeIdBetweenDates(String startDate, String endDate, String employeeId);
}
