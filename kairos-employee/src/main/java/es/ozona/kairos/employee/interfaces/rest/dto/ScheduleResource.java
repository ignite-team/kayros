package es.ozona.kairos.employee.interfaces.rest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import io.swagger.annotations.ApiModelProperty;

public class ScheduleResource {

	@NotEmpty
	@ApiModelProperty(value = "The calendar identifier.", required = false, position = 2, allowEmptyValue = false, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String calendarId;

	@NotEmpty
	@ApiModelProperty(value = "The schedule identifier.", required = false, position = 3, allowEmptyValue = false, example = "2B477572-BD4A-4C28-A504-64C9486492CC")
	private String scheduleId;

	@NotNull
	@ApiModelProperty(value = "The start date of the validity period.", required = true, position = 4, allowEmptyValue = false, example = "12/07/2020")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate startDate;

	@ApiModelProperty(value = "The end date of the validity period.", required = true, position = 5, allowEmptyValue = false, example = "12/07/2020")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate endDate;

	public ScheduleResource() {

	}

	public ScheduleResource(String scheduleId, String calendarId, LocalDate startDate, LocalDate endDate) {
		this.scheduleId = scheduleId;
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;
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
