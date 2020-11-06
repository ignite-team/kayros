package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanDeletedEvent extends AbstractEvent {
	
	private ShiftPlanDeletedEventData shiftPlanDeleteEventData = new ShiftPlanDeletedEventData();

	public ShiftPlanDeletedEvent() {

	}

	public ShiftPlanDeletedEvent(ShiftPlanDeletedEventData shiftPlanDeletedEventData) {
		this.shiftPlanDeleteEventData = shiftPlanDeletedEventData;
	}

	public ShiftPlanDeletedEventData getShiftPlanDeleteEventData() {
		return shiftPlanDeleteEventData;
	}

	public void setShiftPlanDeleteEventData(ShiftPlanDeletedEventData shiftPlanDeleteEventData) {
		this.shiftPlanDeleteEventData = shiftPlanDeleteEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanDeleteEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanDeletedEvent)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
