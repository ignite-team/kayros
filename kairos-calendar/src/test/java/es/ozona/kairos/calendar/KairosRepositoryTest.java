package es.ozona.kairos.calendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = { "spring.h2.console.enabled=true" })
public class KairosRepositoryTest {

	@Autowired
	CalendarRepository repository;

	@BeforeAll
	public static void initTest() throws SQLException {
		Server webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
		webServer.start();
	}

	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void db() {
		final Calendar calendar = new Calendar();
		repository.save(calendar);
		List<Calendar> calendars = new ArrayList<Calendar>();
		repository.findAll().forEach(c -> calendars.add(c));

		assertThat(calendars.size()).isEqualTo(1);

	}
	
}
