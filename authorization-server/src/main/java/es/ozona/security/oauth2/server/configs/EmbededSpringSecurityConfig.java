package es.ozona.security.oauth2.server.configs;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.ContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.server.UnboundIdContainer;

@Configuration
@Profile("dev")
@Order(1)
public class EmbededSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {			
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/oauth/authorize")
			.authenticated()
		.and()
			.formLogin().loginPage("/login")
		.and().requestMatchers()
        	.antMatchers("/login","/oauth/authorize");
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
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
