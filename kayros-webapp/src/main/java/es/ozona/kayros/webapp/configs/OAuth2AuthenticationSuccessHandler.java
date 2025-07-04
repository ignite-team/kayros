package es.ozona.kayros.webapp.configs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("oauth2authSuccessHandler")
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	// @Autowired
	// private UserRepository userRepository;

//	@Autowired
//	private PasswordEncoder encoder;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2AuthenticationToken oToken = (OAuth2AuthenticationToken) authentication;
//		if (userRepository.findByUsername(authentication.getName()) == null) {
//
//			String firstName = oToken.getPrincipal().getAttributes().get("given_name").toString();
//			String lastName = oToken.getPrincipal().getAttributes().get("family_name").toString();
//			String email = oToken.getPrincipal().getAttributes().get("email").toString();
//
//			MyAppUser user = new MyAppUser(email, firstName, lastName, email, encoder.encode("secret"), true);
//			userRepository.save(user);
//		}

		this.redirectStrategy.sendRedirect(request, response, "/secure/main");

	}
}
