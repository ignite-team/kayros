package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanDeletedEvent extends AbstractEvent {
	
	private ShiftplanDeletedEventData shiftplanDeleteEventData = new ShiftplanDeletedEventData();

	public ShiftplanDeletedEvent() {

	}

	public ShiftplanDeletedEvent(ShiftplanDeletedEventData shiftplanDeletedEventData) {
		this.shiftplanDeleteEventData = shiftplanDeletedEventData;
	}

	public ShiftplanDeletedEventData getShiftplanDeleteEventData() {
		return shiftplanDeleteEventData;
	}

	public void setShiftplanDeleteEventData(ShiftplanDeletedEventData shiftplanDeleteEventData) {
		this.shiftplanDeleteEventData = shiftplanDeleteEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanDeleteEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanDeletedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
