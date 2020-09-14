package es.ozona.kairos.employee.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class EmployeeModifiedEvent {
	private EmployeeModifiedEventData employeeModifiedEventData;

	public EmployeeModifiedEvent(EmployeeModifiedEventData employeeModifiedEventData) {
		this.employeeModifiedEventData = employeeModifiedEventData;
	}

	public EmployeeModifiedEventData getEmployeeModifiedEventData() {
		return employeeModifiedEventData;
	}

	public void setEmployeeModifiedEventData(EmployeeModifiedEventData employeeModifiedEventData) {
		this.employeeModifiedEventData = employeeModifiedEventData;
	}

	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeModifiedEventData });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof EmployeeModifiedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
