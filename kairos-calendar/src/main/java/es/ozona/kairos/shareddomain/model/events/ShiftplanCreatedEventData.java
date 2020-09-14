package es.ozona.kairos.shareddomain.model.events;

import java.time.LocalDate;

import org.springframework.util.ObjectUtils;

public class ShiftplanCreatedEventData {
	private String shiftplanId;
	private String calendarId;
	private LocalDate startDate;
	private LocalDate endDate;

	public ShiftplanCreatedEventData() {

	}

	public ShiftplanCreatedEventData(String shiftplanId, String calendarId, LocalDate startDate, LocalDate endDate) {
		this.shiftplanId = shiftplanId;
		this.calendarId = calendarId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getShiftplanId() {
		return shiftplanId;
	}

	public void setShiftplanId(String shiftplanId) {
		this.shiftplanId = shiftplanId;
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

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftplanId, calendarId, startDate, endDate });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanCreatedEventData)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
