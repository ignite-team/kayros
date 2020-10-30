package es.ozona.kairos.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.ozona.kairos.calendar.infrastructure.brokers.CalendarEventSource;
import es.ozona.kairos.calendar.infrastructure.brokers.ShiftPlanEventSource;

@SpringBootApplication(scanBasePackages = "es.ozona.kairos")
@EnableJpaRepositories(basePackages = "es.ozona.kairos.calendar.infrastructure.repositories", repositoryBaseClass = es.ozona.micro.core.infrastructure.respository.BaseRepositoryImpl.class)
@EnableBinding(value = { CalendarEventSource.class, ShiftPlanEventSource.class })
@EnableDiscoveryClient
public class KairosCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(KairosCalendarApplication.class, args);
	}

}
