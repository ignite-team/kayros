package es.ozona.kairos.employee.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class EmployeeCreatedEvent {

	private EmployeeCreatedEventData employeeCreatedEventData;

	public EmployeeCreatedEvent(EmployeeCreatedEventData employeeCreatedEventData) {
		this.employeeCreatedEventData = employeeCreatedEventData;
	}

	public EmployeeCreatedEventData getEmployeeCreatedEventData() {
		return employeeCreatedEventData;
	}

	public void setEmployeeCreatedEventData(EmployeeCreatedEventData employeeCreatedEventData) {
		this.employeeCreatedEventData = employeeCreatedEventData;
	}

	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeCreatedEventData });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof EmployeeCreatedEvent)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
