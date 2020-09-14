package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanWorkdayDeletedEvent extends AbstractEvent {
	private ShiftplanWorkdayDeletedEventData shiftplanWorkdayDeletedEventData;

	public ShiftplanWorkdayDeletedEvent() {

	}

	public ShiftplanWorkdayDeletedEvent(ShiftplanWorkdayDeletedEventData shiftplanWorkdayDeletedEventData) {
		this.shiftplanWorkdayDeletedEventData = shiftplanWorkdayDeletedEventData;
	}

	public ShiftplanWorkdayDeletedEventData getShiftplanWorkdayDeletedEventData() {
		return shiftplanWorkdayDeletedEventData;
	}

	public void setShiftplanWorkdayDeletedEventData(ShiftplanWorkdayDeletedEventData shiftplanWorkdayDeletedEventData) {
		this.shiftplanWorkdayDeletedEventData = shiftplanWorkdayDeletedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanWorkdayDeletedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanWorkdayAddedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
