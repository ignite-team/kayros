package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.Calendar;
import es.ozona.kayros.webapp.shareddomain.model.CalendarResource;

@SpringBootTest
public class CalendarMapperTest {

	private String calendarId;
	private String title;
	private String description;
	private int year;
	private boolean markedAsDefault;

	private CalendarResource calendarResource;

	private Calendar calendar;

	@BeforeEach
	public void init() {

		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		title = "title";
		description = "description";
		year = 2020;
		markedAsDefault = false;

		calendarResource = new CalendarResource(calendarId, title, description, year, markedAsDefault);

		calendar = new Calendar(calendarId, title, description, year, markedAsDefault);

	}

	@Test
	protected void givenCalendarResource_whenCalendarMapperMapFromResourceEqualsCalendar_thenReturnTrue() {

		assertThat(CalendarMapper.mapFromResource(calendarResource)).isEqualTo(calendar);

	}

	@Test
	protected void givenCalendar_whenCalendarMapperMapToResourceEqualsCalendarResource_thenReturnTrue() {

		assertThat(CalendarMapper.mapToResource(calendar)).isEqualTo(calendarResource);

	}

}
