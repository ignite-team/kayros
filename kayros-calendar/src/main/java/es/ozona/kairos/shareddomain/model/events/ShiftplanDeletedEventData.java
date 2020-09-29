package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanDeletedEventData {
	private String shiftplanId;

	public ShiftplanDeletedEventData() {

	}

	public ShiftplanDeletedEventData(String shiftplanId) {
		this.setShiftplanId(shiftplanId);
	}

	public String getShiftplanId() {
		return shiftplanId;
	}

	public void setShiftplanId(String shiftplanId) {
		this.shiftplanId = shiftplanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(shiftplanId);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanDeletedEventData)) {
			return false;
		}

		return this.hashCode() == obj.hashCode();
	}

}
