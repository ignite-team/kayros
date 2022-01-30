package es.ozona.kayros.webapp.vms.filters;

import java.util.Date;

import es.ozona.kayros.webapp.vms.FilterObject;

public class WorkingTimePeriodFilter implements FilterObject {

	@AndExpression()
	private Date startTimeBefore;

	@AndExpression()
	private Date startTimeAfter;

	@AndExpression()
	private Boolean generatedStartTime;

	@AndExpression()
	private Boolean editedStartTime;

	@AndExpression()
	private Date finishTimeBefore;

	@AndExpression()
	private Date finishTimeAfter;

	@AndExpression()
	private Boolean generatedFinishTime;

	@AndExpression()
	private Boolean editedFinishTime;

	@AndExpression()
	private Boolean telecommuting;

	@AndExpression()
	private String workplaceLike;

	public Date getStartTimeBefore() {
		return startTimeBefore;
	}

	public void setStartTimeBefore(Date startTimeBefore) {
		this.startTimeBefore = startTimeBefore;
	}

	public Date getStartTimeAfter() {
		return startTimeAfter;
	}

	public void setStartTimeAfter(Date startTimeAfter) {
		this.startTimeAfter = startTimeAfter;
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

	public Date getFinishTimeBefore() {
		return finishTimeBefore;
	}

	public void setFinishTimeBefore(Date finishTimeBefore) {
		this.finishTimeBefore = finishTimeBefore;
	}

	public Date getFinishTimeAfter() {
		return finishTimeAfter;
	}

	public void setFinishTimeAfter(Date finishTimeAfter) {
		this.finishTimeAfter = finishTimeAfter;
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

	public String getWorkplaceLike() {
		return workplaceLike;
	}

	public void setWorkplaceLike(String workplaceLike) {
		this.workplaceLike = workplaceLike;
	}

	@Override
	public void clear() {
				
	}

}
