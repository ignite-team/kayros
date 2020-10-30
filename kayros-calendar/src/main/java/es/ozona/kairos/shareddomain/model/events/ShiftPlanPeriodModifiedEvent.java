package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanPeriodModifiedEvent extends AbstractEvent {
	private ShiftPlanPeriodModifiedEventData shiftPlanPeriodModifiedEventData;

	public ShiftPlanPeriodModifiedEvent() {

	}

	public ShiftPlanPeriodModifiedEvent(ShiftPlanPeriodModifiedEventData shiftPlanPeriodModifiedEventData) {
		this.shiftPlanPeriodModifiedEventData = shiftPlanPeriodModifiedEventData;
	}

	public ShiftPlanPeriodModifiedEventData getShiftPlanPeriodModifiedEventData() {
		return shiftPlanPeriodModifiedEventData;
	}

	public void setShiftPlanPeriodModifiedEventData(ShiftPlanPeriodModifiedEventData shiftPlanPeriodModifiedEventData) {
		this.shiftPlanPeriodModifiedEventData = shiftPlanPeriodModifiedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanPeriodModifiedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanPeriodModifiedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
