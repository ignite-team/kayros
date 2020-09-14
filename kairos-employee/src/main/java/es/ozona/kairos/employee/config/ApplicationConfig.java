package es.ozona.kairos.employee.config;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import es.ozona.kairos.employee.application.internal.exceptions.NotFoundException;
import es.ozona.kairos.employee.application.internal.exceptions.ServerErrorException;
import es.ozona.kairos.employee.infrastructure.brokers.EmployeeEventSource;
import es.ozona.micro.core.infrastructure.message.resources.Labels;

@Configuration
@EnableJpaRepositories(basePackages = "es.ozona.kairos.employee.infrastructure.repositories", repositoryBaseClass = es.ozona.micro.core.infrastructure.respository.BaseRepositoryImpl.class)
@EnableBinding(EmployeeEventSource.class)
public class ApplicationConfig implements WebMvcConfigurer {

	@Bean
	public ResourceBundleMessageSource messageSource() {

		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages/labels");
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

	@Bean
	public Labels getLabels(MessageSource messageSource) {
		return new Labels(messageSource);
	}

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
					throw new ServerErrorException(response.getStatusText());

				} else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
					throw new NotFoundException(response.getStatusText());
				}

			}
		};

	}

}
