package es.ozona.security.oauth2.server.outboundservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(ExternalUserService.class);

	@Autowired
	UserService userService;
	
//	@Autowired
//	LdapAuthenticationProvider ldapProvider;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userService.findByUsername(username);
		
		if (usuario == null) {
			LOG.error("Error en el login, no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario '" + username + "' en el sistema");
		}

		LOG.info("Usuario autenticado: " + username);

		return new User(usuario.getUsername(), new BCryptPasswordEncoder().encode(usuario.getPassword()), usuario.isEnabled(), true, true, true,
				usuario.getAuthorities());
	}

	public User findByUsername(String username) {
		return userService.findByUsername(username);
	}
}
