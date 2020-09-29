package es.ozona.kairos.employee.domain.model.commands;

import java.time.LocalDate;

public class AssignScheduleCommand {

	private String employeeId;

	private String scheduleId;

	private String calendarId;

	private LocalDate startDate;

	private LocalDate endDate;

	public AssignScheduleCommand() {

	}

	public AssignScheduleCommand(String employeeId, String scheduleId, String calendarId, LocalDate startDate, LocalDate endDate) {
		this.employeeId = employeeId;
		this.scheduleId = scheduleId;
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
