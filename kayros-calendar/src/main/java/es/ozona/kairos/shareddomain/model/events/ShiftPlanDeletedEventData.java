package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanDeletedEventData {
	private String shiftPlanId;

	public ShiftPlanDeletedEventData() {

	}

	public ShiftPlanDeletedEventData(String shiftPlanId) {
		this.setShiftPlanId(shiftPlanId);
	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	public void setShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftPlanId);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanDeletedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
