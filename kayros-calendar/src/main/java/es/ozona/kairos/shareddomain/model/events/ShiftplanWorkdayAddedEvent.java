package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanWorkdayAddedEvent extends AbstractEvent  {

	private ShiftplanWorkdayAddedEventData shiftplanWorkdayAddedEventData;

	public ShiftplanWorkdayAddedEvent() {

	}

	public ShiftplanWorkdayAddedEvent(ShiftplanWorkdayAddedEventData shiftplanWorkdayAddedEventData) {
		this.shiftplanWorkdayAddedEventData = shiftplanWorkdayAddedEventData;
	}

	public ShiftplanWorkdayAddedEventData getShiftplanWorkdayAddedEventData() {
		return shiftplanWorkdayAddedEventData;
	}

	public void setShiftplanWorkdayAddedEventData(ShiftplanWorkdayAddedEventData shiftplanWorkdayAddedEventData) {
		this.shiftplanWorkdayAddedEventData = shiftplanWorkdayAddedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanWorkdayAddedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanWorkdayAddedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
