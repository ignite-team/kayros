package es.ozona.kairos.employee.shareddomain.model.events;

public class UnregisteredTimesheetClockedInEventData {

	private String employeeId;
	private String useraname;

	public UnregisteredTimesheetClockedInEventData() {

	}

	public UnregisteredTimesheetClockedInEventData(String employeeId, String username) {
		this.employeeId = employeeId;
		this.useraname = username;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUseraname() {
		return useraname;
	}

	public void setUseraname(String useraname) {
		this.useraname = useraname;
	}

}
