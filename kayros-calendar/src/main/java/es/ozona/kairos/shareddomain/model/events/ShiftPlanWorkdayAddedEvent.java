package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanWorkdayAddedEvent extends AbstractEvent  {

	private ShiftPlanWorkdayAddedEventData shiftPlanWorkdayAddedEventData;

	public ShiftPlanWorkdayAddedEvent() {

	}

	public ShiftPlanWorkdayAddedEvent(ShiftPlanWorkdayAddedEventData shiftPlanWorkdayAddedEventData) {
		this.shiftPlanWorkdayAddedEventData = shiftPlanWorkdayAddedEventData;
	}

	public ShiftPlanWorkdayAddedEventData getShiftPlanWorkdayAddedEventData() {
		return shiftPlanWorkdayAddedEventData;
	}

	public void setShiftPlanWorkdayAddedEventData(ShiftPlanWorkdayAddedEventData shiftPlanWorkdayAddedEventData) {
		this.shiftPlanWorkdayAddedEventData = shiftPlanWorkdayAddedEventData;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanWorkdayAddedEventData);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanWorkdayAddedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
