package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanCreatedEvent extends AbstractEvent {
	private ShiftPlanCreatedEventData shiftPlanCreatedEventData;

	public ShiftPlanCreatedEvent() {

	}

	public ShiftPlanCreatedEvent(ShiftPlanCreatedEventData shiftPlanCreatedEventData) {
		this.shiftPlanCreatedEventData = shiftPlanCreatedEventData;
	}

	public ShiftPlanCreatedEventData getShiftPlanCreatedEventData() {
		return shiftPlanCreatedEventData;
	}

	public void setShiftPlanCreatedEventData(ShiftPlanCreatedEventData shiftPlanCreatedEventData) {
		this.shiftPlanCreatedEventData = shiftPlanCreatedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanCreatedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanCreatedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
