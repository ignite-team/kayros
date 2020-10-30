package es.ozona.kayros.webapp.domain.model;

import java.util.Date;

import org.springframework.util.ObjectUtils;

public class ShiftPlan {

	private String shiftPlanId;

	private String calendarId;

	private Date startDate;

	private Date endDate;

	public ShiftPlan() {

	}

	public ShiftPlan(String shiftPlanId, String calendarId, Date startDate, Date endDate) {

		super();
		this.shiftPlanId = shiftPlanId;
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;

	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	public void setShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, calendarId, startDate, endDate });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlan)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
