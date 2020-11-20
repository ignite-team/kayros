package es.ozona.kairos.employee.domain.model.commands;

import org.springframework.util.ObjectUtils;

public class UnassignScheduleCommand {

	private String employeeId;
	private String scheduleId;

	public UnassignScheduleCommand(String employeeId, String scheduleId) {
		this.employeeId = employeeId;
		this.scheduleId = scheduleId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeId, scheduleId });
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof UnassignScheduleCommand)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
