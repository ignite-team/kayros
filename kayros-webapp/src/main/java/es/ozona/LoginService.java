package es.ozona;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class LoginService implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6675705235173537868L;

	public boolean login(String username, String password) {
		final UserDetails userDetails = UserDetails.getAdminUserDetails();
		return userDetails.getLogin().equals(username) && userDetails.getPassword().equals(password);
    }

}
