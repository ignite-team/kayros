package es.ozona.kayros.webapp.shareddomain.model;

import java.time.ZonedDateTime;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

public class WorkingTimePeriodResource {

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

	private Boolean telecommuting;

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

	public void setTelecommuting(Boolean teletelecommuting) {

		this.telecommuting = teletelecommuting;

	}

	public String getWorkplace() {

		return workplace;

	}

	public void setWorkplace(String workplace) {

		this.workplace = workplace;

	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(
				new Object[] { startTime, generatedStartTime, editedStartTime, finishTime, generatedFinishTime, editedFinishTime, telecommuting, workplace });

	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof WorkingTimePeriodResource)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}