package es.ozona.kayros.webapp.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class WorkingTimePeriod {

	private Date startTime;

	private Boolean generatedStartTime;

	private Boolean editedStartTime;

	private Date finishTime;

	private Boolean generatedFinishTime;

	private Boolean editedFinishTime;

	private Boolean telecommuting;

	private String workplace;

	public WorkingTimePeriod() {

	}

	public WorkingTimePeriod(Date startTime, Boolean generatedStartTime, Boolean editedStartTime, Date finishTime, Boolean generatedFinishTime,
			Boolean editedFinishTime, Boolean telecommuting, String workplace) {

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
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

	public Date getFinishTime() {

		return finishTime;
	}

	public void setFinishTime(Date finishTime) {

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

	public String getPartialDone() {
		Date endTime = finishTime == null ? new Date() : finishTime;

		return new TemporalDuration(Duration.between(LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault()),
				LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault()))).toString();
	}

	public Date getTotalDone() {
		return null;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public Boolean getTelecommuting() {

		return telecommuting;

	}

	public void setTelecommuting(Boolean telecommuting) {

		this.telecommuting = telecommuting;

	}

}