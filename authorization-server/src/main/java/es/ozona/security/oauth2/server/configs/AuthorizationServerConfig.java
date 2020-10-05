package es.ozona.security.oauth2.server.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@RefreshScope
@Configuration
@EnableAuthorizationServer
@Order(1)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated()")
			.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(env.getProperty("config.security.oauth.client.id"))
			.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
			.authorizedGrantTypes("password", "refresh_token", "authorization_code","client_credentials")
			.scopes("user_info", "read", "write")
			.redirectUris("https://localhost:8443/kayros/login/oauth2/code/kayrosclient")
			.autoApprove(true)
			.accessTokenValiditySeconds(3600)
			.refreshTokenValiditySeconds(3600);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));

		endpoints.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
//		return tokenConverter;
//	}
	
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //converter.setSigningKey("123");
        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
        		new ClassPathResource("oauth2keystore.p12"), "secret".toCharArray());
         converter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2server"));
        return converter;
    }
}
