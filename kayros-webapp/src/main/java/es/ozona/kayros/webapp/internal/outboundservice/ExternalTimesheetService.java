package es.ozona.kayros.webapp.internal.outboundservice;

import java.util.Date;
import java.util.List;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.TimesheetResource;

public interface ExternalTimesheetService {

	TimesheetResource clock(Employee employee);

	List<WorkingTimePeriod> searchCurrentByEmployeeId(String employeeId);

	List<Timesheet> searchTimesheetsByEmployeeIdBetweenDates(String startDate, String endDate, String employeeId);

	Timesheet searchCurrentTimesheetByEmployeeId(String employeeId);

	Timesheet searchTimesheetByEmployeeIdAndDate(Date date, String employeeId);

}
