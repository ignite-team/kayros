package es.ozona.ignite.views;

import java.io.Serializable;

public class UserDetailVO implements Serializable {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -4714167149399438022L;
	private String firstName;
	private String lastName;
	private String login;
	private CheckStatus checkStatus;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public CheckStatus getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(CheckStatus checkStatus) {
		this.checkStatus = checkStatus;
	}

}
