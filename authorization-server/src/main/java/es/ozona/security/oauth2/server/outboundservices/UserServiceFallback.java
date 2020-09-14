package es.ozona.security.oauth2.server.outboundservices;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFallback implements UserService {

	@Override
	public User findByUsername(String username) {
		final User user = new User("xose", "12345", true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		return user;
	}

}
