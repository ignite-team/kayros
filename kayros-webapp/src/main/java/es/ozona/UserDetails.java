package es.ozona;

public class UserDetails {
	private final String login;
	private final String password;

	private UserDetails(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public static UserDetails getAdminUserDetails() {
		return new UserDetails("admin", "admin");
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
}
