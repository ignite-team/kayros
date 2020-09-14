package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarHolidayAddedEvent extends AbstractEvent {

	private CalendarHolidayAddedEventData calendarHolidayAddedEventData;

	public CalendarHolidayAddedEvent() {

	}

	public CalendarHolidayAddedEvent(CalendarHolidayAddedEventData calendarHolidayAddedEventData) {
		this.calendarHolidayAddedEventData = calendarHolidayAddedEventData;
	}

	public void setCalendarHolidayAddedEventData(CalendarHolidayAddedEventData calendarHolidayAddedEventData) {
		this.calendarHolidayAddedEventData = calendarHolidayAddedEventData;
	}

	public CalendarHolidayAddedEventData getCalendarHolidayAddedEventData() {
		return calendarHolidayAddedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarHolidayAddedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarHolidayAddedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
