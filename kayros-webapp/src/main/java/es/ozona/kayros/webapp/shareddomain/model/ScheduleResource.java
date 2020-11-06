package es.ozona.kayros.webapp.shareddomain.model;

import org.springframework.util.ObjectUtils;

public class ScheduleResource {

	private String calendarId;
	private String scheduleId;
	private String startDate;
	private String endDate;

	public ScheduleResource() {

	}

	public ScheduleResource(String calendarId, String scheduleId, String startDate, String endDate) {

		super();
		this.calendarId = calendarId;
		this.scheduleId = scheduleId;
		this.startDate = startDate;
		this.endDate = endDate;

	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
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

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, scheduleId, startDate, endDate });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ScheduleResource)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
