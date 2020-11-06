package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanWorkdayDeletedEvent extends AbstractEvent {
	private ShiftPlanWorkdayDeletedEventData shiftPlanWorkdayDeletedEventData;

	public ShiftPlanWorkdayDeletedEvent() {

	}

	public ShiftPlanWorkdayDeletedEvent(ShiftPlanWorkdayDeletedEventData shiftPlanWorkdayDeletedEventData) {
		this.shiftPlanWorkdayDeletedEventData = shiftPlanWorkdayDeletedEventData;
	}

	public ShiftPlanWorkdayDeletedEventData getShiftPlanWorkdayDeletedEventData() {
		return shiftPlanWorkdayDeletedEventData;
	}

	public void setShiftPlanWorkdayDeletedEventData(ShiftPlanWorkdayDeletedEventData shiftPlanWorkdayDeletedEventData) {
		this.shiftPlanWorkdayDeletedEventData = shiftPlanWorkdayDeletedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanWorkdayDeletedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanWorkdayAddedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
