package es.ozona.kairos.calendar.domain.model.commands;

import org.springframework.util.ObjectUtils;

public class DeleteShiftPlanCommand {
	private String shiftPlanId;

	public DeleteShiftPlanCommand() {

	}

	public DeleteShiftPlanCommand(String shiftPlanId) {
		this.shiftPlanId = shiftPlanId;
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

		if (obj == null || !(obj instanceof DeleteShiftPlanCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
