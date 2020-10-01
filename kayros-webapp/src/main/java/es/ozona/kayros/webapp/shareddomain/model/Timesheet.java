
package es.ozona.kayros.webapp.shareddomain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Timesheet {

	private String timesheetId;

	private String employeeId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
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

	public void setWorkingTimePeriods(List<WorkingTimePeriod> workingTimePeriods) {
		this.workingTimePeriods = workingTimePeriods;
	}
}
