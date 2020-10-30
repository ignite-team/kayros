package es.ozona.kayros.webapp.shareddomain.model;

import org.springframework.util.ObjectUtils;

public class ShiftPlanResource {

	private String shiftPlanId;

	private String calendarId;

	private String startDate;

	private String endDate;

	public ShiftPlanResource() {

	}

	public ShiftPlanResource(String shiftPlanId, String calendarId, String startDate, String endDate) {

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, calendarId, startDate, endDate });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanResource)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
