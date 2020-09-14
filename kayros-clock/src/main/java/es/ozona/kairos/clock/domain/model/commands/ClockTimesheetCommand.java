package es.ozona.kairos.clock.domain.model.commands;

import java.time.ZonedDateTime;

public class ClockTimesheetCommand {

	private String employeeId;
	
	private String username;

	private ZonedDateTime clockTime;

	public ClockTimesheetCommand() {
		
	}

	public ClockTimesheetCommand(String employeeId, String username) {
		super();
		this.employeeId = employeeId;
		this.username = username;
		this.clockTime = ZonedDateTime.now();
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

}
