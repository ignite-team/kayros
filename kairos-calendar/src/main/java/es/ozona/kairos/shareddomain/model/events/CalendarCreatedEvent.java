package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class CalendarCreatedEvent extends AbstractEvent {

	private CalendarCreatedEventData calendarCreatedEventData;

	public CalendarCreatedEvent() {

	}

	public CalendarCreatedEvent(CalendarCreatedEventData calendarCreatedEventData) {
		this.calendarCreatedEventData = calendarCreatedEventData;
	}

	public void setCalendarCreatedEventData(CalendarCreatedEventData calendarCreatedEventData) {
		this.calendarCreatedEventData = calendarCreatedEventData;
	}

	public CalendarCreatedEventData getCalendarCreatedEventData() {
		return calendarCreatedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarCreatedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarCreatedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
