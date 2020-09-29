package es.ozona.kairos.clock.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import es.ozona.kairos.clock.infrastructure.brokers.TimesheetEventSource;
import es.ozona.micro.core.infrastructure.message.resources.Labels;

@Configuration
@EnableJpaRepositories(basePackages = "es.ozona.kairos.clock.infrastructure.repositories", repositoryBaseClass = es.ozona.micro.core.infrastructure.respository.BaseRepositoryImpl.class)
@EnableBinding(TimesheetEventSource.class)
public class ApplicationConfig implements WebMvcConfigurer {

	@Bean
	public ResourceBundleMessageSource messageSource() {

		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("messages/labels");
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

	@Bean
	@Scope("singleton")
	public Labels getLabels(MessageSource messageSource) {
		return new Labels(messageSource);
	}

}
