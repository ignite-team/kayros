package es.ozona.kairos.clock.domain.model.commands;

import java.time.LocalDate;

public class CreateTimesheetCommand {

	private String timesheetId;

	private String employeeId;

	private String username;

	private LocalDate date;

	private Boolean telecommuting;

	private String workplace;

	public CreateTimesheetCommand() {

	}

	public CreateTimesheetCommand(String timesheetId, String employeeId, String username, LocalDate date, Boolean telecommuting, String workplace) {

		super();
		this.timesheetId = timesheetId;
		this.employeeId = employeeId;
		this.username = username;
		this.date = date;
		this.telecommuting = telecommuting;
		this.workplace = workplace;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getTelecommuting() {

		return telecommuting;

	}

	public void setTelecommuting(Boolean telecommuting) {

		this.telecommuting = telecommuting;

	}

	public String getWorkplace() {

		return workplace;

	}

	public void setWorkplace(String workplace) {

		this.workplace = workplace;

	}

}
