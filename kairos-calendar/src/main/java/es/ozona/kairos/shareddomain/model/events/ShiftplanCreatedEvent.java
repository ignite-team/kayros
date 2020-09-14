package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanCreatedEvent extends AbstractEvent {
	private ShiftplanCreatedEventData shiftplanCreatedEventData;

	public ShiftplanCreatedEvent() {

	}

	public ShiftplanCreatedEvent(ShiftplanCreatedEventData shiftplanCreatedEventData) {
		this.shiftplanCreatedEventData = shiftplanCreatedEventData;
	}

	public ShiftplanCreatedEventData getShiftplanCreatedEventData() {
		return shiftplanCreatedEventData;
	}

	public void setShiftplanCreatedEventData(ShiftplanCreatedEventData shiftplanCreatedEventData) {
		this.shiftplanCreatedEventData = shiftplanCreatedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanCreatedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanCreatedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
