package es.ozona.kayros.webapp.domain.model;

import java.util.Date;

import org.springframework.util.ObjectUtils;

public class Schedule {

	private String calendarId;
	private String scheduleId;
	private Date startDate;
	private Date endDate;

	public Schedule() {

	}

	public Schedule(String calendarId, String scheduleId, Date startDate, Date endDate) {

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

		return ObjectUtils.nullSafeHashCode(new Object[] { calendarId, scheduleId, startDate, endDate });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Schedule)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
