
package es.ozona.kayros.webapp.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Timesheet {

	private String timesheetId;

	private String employeeId;
	
	private LocalDate date;

	private List<WorkingTimePeriod> workingTimePeriods = new ArrayList<WorkingTimePeriod>(0);

	public Timesheet() {

	}

	public String getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(String timesheetId) {
		this.timesheetId = timesheetId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<WorkingTimePeriod> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriod(List<WorkingTimePeriod> workingTimePeriods) {
		this.workingTimePeriods = workingTimePeriods;
	}
}
