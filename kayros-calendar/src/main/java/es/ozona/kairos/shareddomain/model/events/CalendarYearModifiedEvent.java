package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarYearModifiedEvent extends AbstractEvent {

	private CalendarYearModifiedEventData calendarYearModifiedEventData;

	public CalendarYearModifiedEvent() {

	}

	public CalendarYearModifiedEvent(CalendarYearModifiedEventData calendarYearModifiedEventData) {
		this.calendarYearModifiedEventData = calendarYearModifiedEventData;
	}

	public void setCalendarYearModifiedEventData(CalendarYearModifiedEventData calendarYearModifiedEventData) {
		this.calendarYearModifiedEventData = calendarYearModifiedEventData;
	}

	public CalendarYearModifiedEventData getCalendarYearModifiedEventData() {
		return calendarYearModifiedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarYearModifiedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarYearModifiedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
