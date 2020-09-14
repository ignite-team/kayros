package es.ozona.kairos.calendar.domain.model.commands;

import org.springframework.util.ObjectUtils;

public class DeleteShiftplanCommand {
	private String shiftplanId;

	public DeleteShiftplanCommand() {

	}

	public DeleteShiftplanCommand(String shiftplanId) {
		this.shiftplanId = shiftplanId;
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

		if (obj == null || !(obj instanceof DeleteShiftplanCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
