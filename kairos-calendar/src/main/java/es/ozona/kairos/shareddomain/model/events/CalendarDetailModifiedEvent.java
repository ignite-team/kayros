package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

/**
 * The class that represents the event generated in a calendar detail modification operation.
 * 
 * @author Xose
 * @since 1.0.0
 */
public class CalendarDetailModifiedEvent extends AbstractEvent {

	private CalendarDetailModifiedEventData calendarDetailModifiedEventData;

	/**
	 * Default constructor
	 */
	public CalendarDetailModifiedEvent() {

	}

	/**
	 * Creates a event with given data.
	 * 
	 * @param calendarDetailModifiedEventData
	 */
	public CalendarDetailModifiedEvent(CalendarDetailModifiedEventData calendarDetailModifiedEventData) {
		this.calendarDetailModifiedEventData = calendarDetailModifiedEventData;
	}

	/**
	 * Sets an event data.
	 * 
	 * @param calendarDetailModifiedEventData an event data value
	 */
	public void setCalendarDetailModifiedEventData(CalendarDetailModifiedEventData calendarDetailModifiedEventData) {
		this.calendarDetailModifiedEventData = calendarDetailModifiedEventData;
	}

	/**
	 * Gets an event data.
	 * 
	 * @return the value
	 */
	public CalendarDetailModifiedEventData getCalendarDetailModifiedEventData() {
		return calendarDetailModifiedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(calendarDetailModifiedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof CalendarDetailModifiedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
