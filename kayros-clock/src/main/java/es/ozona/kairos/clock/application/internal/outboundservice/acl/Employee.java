package es.ozona.kairos.clock.application.internal.outboundservice.acl;

import java.io.Serializable;

import org.springframework.util.ObjectUtils;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private String employeeId;
	private String username;
	private String email;
	private String firstname;
	private String lastname;

	private Boolean telecommuting;
	private String workplace;

	public Employee() {

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

		return ObjectUtils.nullSafeHashCode(new Object[] { employeeId, username, email, firstname, lastname, telecommuting, workplace });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Employee)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
