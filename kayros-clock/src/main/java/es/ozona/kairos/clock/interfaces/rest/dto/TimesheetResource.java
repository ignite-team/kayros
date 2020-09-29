package es.ozona.kairos.clock.interfaces.rest.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A Timesheet resource")
public class TimesheetResource {

	@ApiModelProperty(value = "The timesheet ID", required = true, allowEmptyValue = false, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String timesheetId;

	@ApiModelProperty(value = "The employee ID", required = true, allowEmptyValue = false, example = "2B477572-BD4A-4C28-A504-64C94864564C")
	private String employeeId;

	@ApiModelProperty(value = "The timesheet's date", required = true, allowEmptyValue = false, example = "06/07/2020")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;

	@ApiModelProperty(value = "The working time periods list", required = true)
	private List<WorkingTimePeriodResource> workingTimePeriods = new ArrayList<WorkingTimePeriodResource>(0);

	public TimesheetResource() {

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

	public List<WorkingTimePeriodResource> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriods(List<WorkingTimePeriodResource> workingTimePeriods) {
		this.workingTimePeriods = workingTimePeriods;
	}
}
