package es.ozona.kayros.webapp.configs;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(ResponseErrorHandler responseErrorHandler) {

		return new RestTemplateBuilder().errorHandler(responseErrorHandler).build();
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

}
