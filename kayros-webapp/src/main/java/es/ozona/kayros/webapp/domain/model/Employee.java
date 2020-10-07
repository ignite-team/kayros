package es.ozona.kayros.webapp.domain.model;

import org.springframework.util.ObjectUtils;

public class Employee {

	private String employeeId;

	private String username;

	private String email;

	private String firstname;

	private String lastname;

	private String workplace;

	private Boolean telecommuting;
	
	public Employee() {

	}

	public Employee(String employeeId, String username, String email, String firstname, String lastname, String workplace, Boolean telecommuting) {
		this.employeeId = employeeId;
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.telecommuting = telecommuting;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public Boolean getTelecommuting() {
		
		return telecommuting;
		
	}
	
	public void setTelecommuting(Boolean telecommuting) {
		
		this.telecommuting = telecommuting;
		
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] {employeeId, username, email, firstname, lastname, workplace, telecommuting});

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Employee)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
