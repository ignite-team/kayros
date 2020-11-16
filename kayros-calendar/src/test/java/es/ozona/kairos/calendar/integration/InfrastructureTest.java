package es.ozona.kairos.calendar.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;

@SpringBootTest
public class InfrastructureTest {

	@Autowired
	private CalendarRepository repository;

	public void insertCalendar() {
		repository.save(new Calendar());
	}

}
