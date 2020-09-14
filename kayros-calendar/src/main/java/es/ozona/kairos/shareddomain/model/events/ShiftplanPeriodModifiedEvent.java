package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanPeriodModifiedEvent extends AbstractEvent {
	private ShiftplanPeriodModifiedEventData shiftplanPeriodModifiedEventData;

	public ShiftplanPeriodModifiedEvent() {

	}

	public ShiftplanPeriodModifiedEvent(ShiftplanPeriodModifiedEventData shiftplanPeriodModifiedEventData) {
		this.shiftplanPeriodModifiedEventData = shiftplanPeriodModifiedEventData;
	}

	public ShiftplanPeriodModifiedEventData getShiftplanPeriodModifiedEventData() {
		return shiftplanPeriodModifiedEventData;
	}

	public void setShiftplanPeriodModifiedEventData(ShiftplanPeriodModifiedEventData shiftplanPeriodModifiedEventData) {
		this.shiftplanPeriodModifiedEventData = shiftplanPeriodModifiedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanPeriodModifiedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanPeriodModifiedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
