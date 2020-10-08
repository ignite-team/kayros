package es.ozona.kairos.clock.domain.model.commands;

import java.time.ZonedDateTime;

public class ClockTimesheetCommand {

	private String employeeId;

	private String username;

	private ZonedDateTime clockTime;

	private Boolean telecommuting;

	private String workplace;

	public ClockTimesheetCommand() {

	}

	public ClockTimesheetCommand(String employeeId, String username, Boolean telecommuting, String workplace) {

		super();
		this.employeeId = employeeId;
		this.username = username;
		this.clockTime = ZonedDateTime.now();
		this.telecommuting = telecommuting;
		this.workplace = workplace;

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

	public ZonedDateTime getClockTime() {
		return clockTime;
	}

	public void setClockTime(ZonedDateTime clockTime) {
		this.clockTime = clockTime;
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
