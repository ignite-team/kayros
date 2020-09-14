package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftplanWorkdayDeletedEventData {
	private Long id;
	private String shiftplanId;

	public ShiftplanWorkdayDeletedEventData() {

	}

	public ShiftplanWorkdayDeletedEventData(Long id, String shiftplanId) {
		this.id = id;
		this.shiftplanId = shiftplanId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShiftplanId() {
		return shiftplanId;
	}

	public void setShiftplanId(String shiftplanId) {
		this.shiftplanId = shiftplanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftplanId, id });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftplanWorkdayAddedEventData)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
