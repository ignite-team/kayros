package es.ozona.kayros.webapp.configs;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.function.Supplier;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationConfig implements WebMvcConfigurer {

//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//
//		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//		source.setBasenames("messages/labels");
//		source.setUseCodeAsDefaultMessage(true);
//
//		return source;
//	}
//
//	@Bean
//	public Labels getLabels(MessageSource messageSource) {
//		return new Labels(messageSource);
//	}

//	@Value(value = "${server.ssl.key-store}")
//	Resource keyStore = null;
//	@Value(value = "${server.ssl.trust-store}")
//	Resource trustStore = null;

//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate(ResponseErrorHandler responseErrorHandler) throws Exception {
//
////        SSLContext sslContext = SSLContextBuilder
////                .create()
////                .loadKeyMaterial(keyStore.getFile(), "zWx6bv-YTePQ%1qtGtnI".toCharArray(), "secret".toCharArray())
////                .loadTrustMaterial(trustStore.getFile(), "zWx6bv-YTePQ%1qtGtnI".toCharArray())
////                .build();
////
////        HttpClient client = HttpClients.custom()
////                .setSSLContext(sslContext)
////                .build();
////        
////        return new RestTemplateBuilder().errorHandler(responseErrorHandler)
////        		.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
////        		.build();
//
//		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//		requestFactory.setHttpClient(httpClient);
//		
//		return new RestTemplate(requestFactory);
////		return new RestTemplateBuilder().errorHandler(responseErrorHandler)
////		.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
////		.build();
//		
//		
//	}
	
    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient(RestTemplate restTemplate){
        
    	DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();  
        
        accessTokenResponseClient.setRestOperations(new RestTemplateBuilder()
				.requestFactory(myRequestFactorySupplier())
				.errorHandler(getResponseErrorHandler())
				.messageConverters(Arrays.asList(
						new FormHttpMessageConverter(), new OAuth2AccessTokenResponseHttpMessageConverter()))
				.build());
        return accessTokenResponseClient;
    }
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder, ResponseErrorHandler errorHandler) {
		return builder
				.requestFactory(myRequestFactorySupplier())
				.errorHandler(errorHandler)
				.build();
	}

	@Bean
	public MyRequestFactorySupplier myRequestFactorySupplier() {
		return new MyRequestFactorySupplier();
	}

	class MyRequestFactorySupplier implements Supplier<ClientHttpRequestFactory> {

		@Override
		public ClientHttpRequestFactory get() {
			HttpComponentsClientHttpRequestFactory requestFactory = null;
			try {
				char[] password = "secret".toCharArray();
				KeyStore ks = KeyStore.getInstance("PKCS12");
				InputStream ksStream = this.getClass().getClassLoader().getResourceAsStream("keystore.p12");
				ks.load(ksStream, password);

				SSLContext sslContext = new SSLContextBuilder()
						.loadTrustMaterial(this.getClass().getClassLoader().getResource("truststore.p12"), password)
						.loadKeyMaterial(ks, password).build();

				SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
						NoopHostnameVerifier.INSTANCE);

				CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(socketFactory).build();

				requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
				requestFactory.setBufferRequestBody(false);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return requestFactory;
		}

	}

	@Bean
	public ResponseErrorHandler getResponseErrorHandler() {
		return new ResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return (response.getStatusCode().series() == Series.CLIENT_ERROR || response.getStatusCode().series() == Series.SERVER_ERROR);
			}

			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
					throw new RuntimeException(response.getStatusText());

				} else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
					throw new RuntimeException(response.getStatusText());
				}

			}
		};

	}

	@Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> customUserService() {
    	final DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
    	final RestTemplate restOperations = new RestTemplateBuilder()
    			.requestFactory(myRequestFactorySupplier())
    			.errorHandler(new OAuth2ErrorResponseErrorHandler())
    			.build();
    	
    	userService.setRestOperations(restOperations);
    	
    	return userService;
    }
}
