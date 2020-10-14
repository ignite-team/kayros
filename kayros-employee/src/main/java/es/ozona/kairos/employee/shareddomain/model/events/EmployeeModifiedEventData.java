package es.ozona.kairos.employee.shareddomain.model.events;

import org.springframework.util.ObjectUtils;

public class EmployeeModifiedEventData {

	private String employeeId;

	private String lastname;

	private String email;

	private String firstname;

	private String surename;

	private Boolean telecommuting;
	
	private String workplace;

	public EmployeeModifiedEventData(String employeeId, String lastname, String email, String firstname, String surename, Boolean telecommuting, String workplace) {

		super();
		this.employeeId = employeeId;
		this.lastname = lastname;
		this.email = email;
		this.firstname = firstname;
		this.surename = surename;
		this.telecommuting = telecommuting;
		this.workplace = workplace;

	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

	public Boolean getTelecommuting() {

		return telecommuting;

	}

	public void setTelecommuting(Boolean telecommuting) {

		this.telecommuting = telecommuting;

	}
	
	public String getWorkplace() {
		
		return workplace;
		
	}

	public void setWorkplace(String workplace) {

		this.workplace = workplace;
		
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] {employeeId, lastname, email, firstname, surename, telecommuting, workplace});

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof EmployeeModifiedEventData)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
