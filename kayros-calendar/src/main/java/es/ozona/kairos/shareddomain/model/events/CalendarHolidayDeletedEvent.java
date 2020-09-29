package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarHolidayDeletedEvent extends AbstractEvent {

	private CalendarHolidayDeletedEventData calendarHolidayDeletedEventData;

	public CalendarHolidayDeletedEvent() {

	}

	public CalendarHolidayDeletedEvent(CalendarHolidayDeletedEventData calendarHolidayDeletedEventData) {
		this.calendarHolidayDeletedEventData = calendarHolidayDeletedEventData;
	}

	public CalendarHolidayDeletedEventData getCalendarHolidayDeletedEventData() {
		return calendarHolidayDeletedEventData;
	}

	public void setCalendarHolidayDeletedEventData(CalendarHolidayDeletedEventData calendarHolidayDeletedEventData) {
		this.calendarHolidayDeletedEventData = calendarHolidayDeletedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarHolidayDeletedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarHolidayDeletedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
