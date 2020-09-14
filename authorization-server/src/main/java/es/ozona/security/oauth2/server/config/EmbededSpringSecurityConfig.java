package es.ozona.security.oauth2.server.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.server.UnboundIdContainer;

@Configuration
@Profile("dev")
public class EmbededSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=users")
				// .userSearchFilter("sAMAccountName={0}")
				.groupSearchBase("ou=groups")
				.groupSearchFilter("uniqueMember={0}").contextSource()
				.url("ldap://localhost:8389/dc=xilco,dc=gal")
//				.managerPassword("hAA8GdNQ")
//				.managerDn("admin")
//				.and()
//				.passwordCompare()
//					.passwordEncoder(passwordEncoder())
//					.passwordAttribute("userPassword")
		;
	}

	@Bean
	protected UnboundIdContainer ldapContainer() {
		UnboundIdContainer container = new UnboundIdContainer("dc=xilco,dc=gal", "classpath:users.ldif");
		container.setPort(8389);
		return container;
	}

	@Bean
	protected ContextSource contextSource(UnboundIdContainer container) {
		return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389"), "dc=xilco,dc=gal");
	}

}
