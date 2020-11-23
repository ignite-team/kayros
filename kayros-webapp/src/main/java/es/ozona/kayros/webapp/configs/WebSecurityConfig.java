package es.ozona.kayros.webapp.configs;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

/**
 * This is an example of minimal configuration for ZK + Spring Security, we open as less access as possible to run a ZK-based application. Please understand the
 * configuration and modify it upon your requirement.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ZUL_FILES = "/zkau/web/**/*.zul";
	private static final String CMD = "cmd_0";
	
	private static final String[] ZK_RESOURCES = { 
		"/zkau/web/**/js/**",
		"/zkau/web/**/zul/js/**",
		"/zkau/web/**/css/**",
		"/zkau/web/**/zul/css/**",
		"/zkau/web/**/font/**",
		"/zkau/web/**/zul/font/**",
		"/zkau/web/**/img/**",
		"/zkau/web/**/zul/img/**"
	};

	// allow desktop cleanup after logout or when reloading login page
	private static final String REMOVE_DESKTOP_REGEX = "/zkau\\?dtid=.*&cmd_0=rmDesktop&.*";

	@Autowired
	protected OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	
	@Autowired
	protected OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient;
	
	@Autowired
	OAuth2UserService<OAuth2UserRequest, OAuth2User> customUserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// ZK already sends a AJAX request with a built-in CSRF token,
		// please refer to https://www.zkoss.org/wiki/ZK%20Developer's%20Reference/Security%20Tips/Cross-site%20Request%20Forgery
		http.csrf().disable();

		http.authorizeRequests().antMatchers(ZUL_FILES).denyAll() // block direct access to zul files
				.antMatchers(HttpMethod.GET, ZK_RESOURCES).permitAll() // allow zk resources
				.requestMatchers(req -> "dummy".equals(req.getParameter(CMD)) || "onMove".equals(req.getParameter(CMD))
						|| "rmDesktop".equals(req.getParameter(CMD)))
				.permitAll() // allow zk resources
				.regexMatchers(HttpMethod.GET, REMOVE_DESKTOP_REGEX).permitAll() // allow desktop cleanup

				.requestMatchers(req -> "rmDesktop".equals(req.getParameter("cmd_0"))).permitAll() // allow desktop cleanup from ZATS
				.mvcMatchers("/login", "/logout").permitAll().mvcMatchers("/", "/secure/**")
				.authenticated().anyRequest()
				.authenticated()
				.and().logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/")
				.and().oauth2Login()
				      .loginPage("/login").successHandler(oAuth2AuthenticationSuccessHandler)				      
				      .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient)
				.and().userInfoEndpoint().userService(customUserService);
				      
	}

	@Bean
	public GrantedAuthoritiesMapper userAuthoritiesMapper() {
		return (authorities) -> {
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

			authorities.forEach(authority -> {
				if (OidcUserAuthority.class.isInstance(authority)) {
					OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;

					OidcIdToken idToken = oidcUserAuthority.getIdToken();
					OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

					// Map the claims found in idToken and/or userInfo
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				} else if (OAuth2UserAuthority.class.isInstance(authority)) {
					OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority) authority;

					Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
					Object obj = oauth2UserAuthority.getAttributes().get("authorities");

					List<Map<String, Object>> map = (List<Map<String, Object>>) obj;

					for (Map<String, Object> auth : map) {
						mappedAuthorities.add(new SimpleGrantedAuthority(auth.get("authority").toString()));
					}
					// Map the attributes found in userAttributes
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				}
			});

			return mappedAuthorities;
		};
	}

}