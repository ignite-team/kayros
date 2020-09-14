package es.ozona.security.oauth2.server.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
@Profile("test")
public class LdapSpringSecurityConfig extends WebSecurityConfigurerAdapter {

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
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		final BaseLdapPathContextSource contextSource = (BaseLdapPathContextSource) this.contextSource();
		auth.authenticationProvider(
				authenticationProvider(this.authenticator(contextSource), authorities(contextSource)));
	}

	@Bean
	protected ContextSource contextSource(/* UnboundIdContainer container */) {
		final DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(
				Arrays.asList("ldap://192.168.0.22:389"), "ou=kayros,dc=xilcorp,dc=local");
		contextSource.setPassword("hAA8GdNQ");
		contextSource.setUserDn("admin");

		return contextSource;
	}

	@Bean
	BindAuthenticator authenticator(BaseLdapPathContextSource contextSource) {
		final BindAuthenticator authenticator = new BindAuthenticator(contextSource);
		authenticator.setUserDnPatterns(new String[] { "cn=*,ou=users" });
		authenticator.setUserSearch(new FilterBasedLdapUserSearch("", "sAMAccountName={0}", contextSource));
		return authenticator;
	}

	@Bean
	LdapAuthenticationProvider authenticationProvider(LdapAuthenticator authenticator) {
		return new LdapAuthenticationProvider(authenticator);
	}

	@Bean
	LdapAuthoritiesPopulator authorities(BaseLdapPathContextSource contextSource) {
		final String groupSearchBase = "ou=groups";
		final DefaultLdapAuthoritiesPopulator authorities = new DefaultLdapAuthoritiesPopulator(contextSource,
				groupSearchBase);
		authorities.setGroupSearchFilter("member={0}");
		return authorities;
	}

	@Bean
	LdapAuthenticationProvider authenticationProvider(LdapAuthenticator authenticator,
			LdapAuthoritiesPopulator authorities) {
		return new LdapAuthenticationProvider(authenticator, authorities);
	}

	public LdapTemplate ldapTemplate(ContextSource contextSource) {
		return new LdapTemplate(contextSource);
	}

}
