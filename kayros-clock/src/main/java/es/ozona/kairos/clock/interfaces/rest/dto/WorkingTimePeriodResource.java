package es.ozona.kairos.clock.interfaces.rest.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "A working time period resource")
public class WorkingTimePeriodResource {

	@ApiModelProperty(value = "The start time", required = true, example = "06/07/2020 08:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu'T'HH:mm:ss:SSSXXXXX")
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime startTime;

	@ApiModelProperty(value = "Start time value has been generated.", required = true, example = "false")
	private Boolean generatedStartTime;

	@ApiModelProperty(value = "Start time value has been edited.", required = true, example = "false")
	private Boolean editedStartTime;

	@ApiModelProperty(value = "The finish time.", required = false, example = "06/07/2020 05:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu'T'HH:mm:ss:SSSXXXXX")
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime finishTime;

	@ApiModelProperty(value = "Finish time value has been generated.", required = false, example = "false")
	private Boolean generatedFinishTime;

	@ApiModelProperty(value = "Finish time value has been edited.", required = false, example = "false")
	private Boolean editedFinishTime;

	@ApiModelProperty(value = "telecommuting", required = true, example = "true")
	private Boolean telecommuting;

	@ApiModelProperty(value = "workplace", required = true, example = "San Marcos")
	private String workplace;

	public WorkingTimePeriodResource() {

	}

	public WorkingTimePeriodResource(ZonedDateTime startTime, Boolean generatedStartTime, Boolean editedStartTime, ZonedDateTime finishTime,
			Boolean generatedFinishTime, Boolean editedFinishTime, Boolean telecommuting, String workplace) {

		super();
		this.startTime = startTime;
		this.generatedStartTime = generatedStartTime;
		this.editedStartTime = editedStartTime;
		this.finishTime = finishTime;
		this.generatedFinishTime = generatedFinishTime;
		this.editedFinishTime = editedFinishTime;
		this.telecommuting = telecommuting;
		this.workplace = workplace;

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

	public String getWorkplace() {

		return workplace;

	}

	public void setWorkplace(String workplace) {

		this.workplace = workplace;

	}

}
