package es.ozona.kairos.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class ShiftPlanWorkdayDeletedEventData {
	private Long id;
	private String shiftPlanId;

	public ShiftPlanWorkdayDeletedEventData() {

	}

	public ShiftPlanWorkdayDeletedEventData(Long id, String shiftPlanId) {
		this.id = id;
		this.shiftPlanId = shiftPlanId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShiftPlanId() {
		return shiftPlanId;
	}

	public void setShiftPlanId(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { shiftPlanId, id });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof ShiftPlanWorkdayAddedEventData)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
