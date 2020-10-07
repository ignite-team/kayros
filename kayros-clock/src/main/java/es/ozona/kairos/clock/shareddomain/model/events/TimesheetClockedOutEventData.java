package es.ozona.kairos.clock.shareddomain.model.events;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class TimesheetClockedOutEventData {

	private String employeeId;

	private String timesheetId;

	private LocalDate date;

	private ZonedDateTime startTime;

	private Boolean generatedStartTime;

	private Boolean editedStartTime;

	private ZonedDateTime finishTime;

	private Boolean generatedFinishTime;

	private Boolean editedFinishTime;
	
	private Boolean telecommuting;
	
	public TimesheetClockedOutEventData() {
		
	}

	public TimesheetClockedOutEventData(String employeeId, String timesheetId, LocalDate date, ZonedDateTime startTime, Boolean generatedStartTime, Boolean editedStartTime, ZonedDateTime finishTime, Boolean generatedFinishTime, Boolean editedFinishTime, Boolean telecommuting) {
		super();
		this.employeeId = employeeId;
		this.timesheetId = timesheetId;
		this.date = date;
		this.startTime = startTime;
		this.generatedStartTime = generatedStartTime;
		this.editedStartTime = editedStartTime;
		this.finishTime = finishTime;
		this.generatedFinishTime = generatedFinishTime;
		this.editedFinishTime = editedFinishTime;
		this.telecommuting = telecommuting;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(String timesheetId) {
		this.timesheetId = timesheetId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public Boolean getGeneratedStartTime() {
		return generatedStartTime;
	}

	public void setGeneratedStartTime(Boolean generatedStartTime) {
		this.generatedStartTime = generatedStartTime;
	}

	public Boolean getEditedStartTime() {
		return editedStartTime;
	}

	public void setEditedStartTime(Boolean editedStartTime) {
		this.editedStartTime = editedStartTime;
	}

	public ZonedDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(ZonedDateTime finishTime) {
		this.finishTime = finishTime;
	}

	public Boolean getGeneratedFinishTime() {
		return generatedFinishTime;
	}

	public void setGeneratedFinishTime(Boolean generatedFinishTime) {
		this.generatedFinishTime = generatedFinishTime;
	}

	public Boolean getEditedFinishTime() {
		return editedFinishTime;
	}

	public void setEditedFinishTime(Boolean editedFinishTime) {
		this.editedFinishTime = editedFinishTime;
	}

	public Boolean getTelecommuting() {

		return telecommuting;
		
	}

	public void setTelecommuting(Boolean telecommuting) {
		
		this.telecommuting = telecommuting;
		
	}

}
