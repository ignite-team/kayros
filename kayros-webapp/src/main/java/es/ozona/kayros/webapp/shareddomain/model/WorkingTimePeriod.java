package es.ozona.kayros.webapp.shareddomain.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

public class WorkingTimePeriod {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu'T'HH:mm:ss:SSSXXXXX")
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
	private ZonedDateTime startTime;

	private Boolean generatedStartTime;

	private Boolean editedStartTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/uuuu'T'HH:mm:ss:SSSXXXXX")
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	@JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
	private ZonedDateTime finishTime;

	private Boolean generatedFinishTime;

	private Boolean editedFinishTime;

	public WorkingTimePeriod() {

	}

	public WorkingTimePeriod(ZonedDateTime startTime, Boolean generatedStartTime, Boolean editedStartTime,
			ZonedDateTime finishTime, Boolean generatedFinishTime, Boolean editedFinishTime) {
		super();
		this.startTime = startTime;
		this.generatedStartTime = generatedStartTime;
		this.editedStartTime = editedStartTime;
		this.finishTime = finishTime;
		this.generatedFinishTime = generatedFinishTime;
		this.editedFinishTime = editedFinishTime;
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

}