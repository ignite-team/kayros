package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalendarTest {

	private String calendarId;
	private String title;
	private String description;
	private int year;
	private boolean markedAsDefault;

	private Calendar emptyCalendar;
	private Calendar calendar;

	@BeforeEach
	public void init() {

		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		title = "title";
		description = "description";
		year = 2020;
		markedAsDefault = false;

		emptyCalendar = new Calendar();
		calendar = new Calendar(calendarId, title, description, year, markedAsDefault);

	}

	@Test
	protected void givenCalendar_whenCalendarGetCalendarId_thenReturnCalendarId() {

		assertThat(calendar.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenCalendar_whenCalendarGetTitle_thenReturnTitle() {

		assertThat(calendar.getTitle()).isEqualTo(title);

	}

	@Test
	protected void givenCalendar_whenCalendarGetDescription_thenReturnDescription() {

		assertThat(calendar.getDescription()).isEqualTo(description);

	}

	@Test
	protected void givenCalendar_whenCalendarGetYear_thenReturnYear() {

		assertThat(calendar.getYear()).isEqualTo(year);

	}

	@Test
	protected void givenCalendar_whenCalendarGetMarkedAsDefault_thenReturnMarkedAsDefault() {

		assertThat(calendar.getMarkedAsDefault()).isEqualTo(markedAsDefault);

	}

	@Test
	protected void givenEmptyCalendar_whenEmptyCalendarSetCalendarIdAndGetCalendarId_thenReturnCalendarId() {

		emptyCalendar.setCalendarId(calendarId);
		assertThat(emptyCalendar.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	protected void givenEmptyCalendar_whenEmptyCalendarSetTitleAndGetTitle_thenReturnTitle() {

		emptyCalendar.setTitle(title);
		assertThat(emptyCalendar.getTitle()).isEqualTo(title);

	}

	@Test
	protected void givenEmptyCalendar_whenEmptyCalendarSetDescriptionAndGetDescription_thenReturnDescription() {

		emptyCalendar.setDescription(description);
		assertThat(emptyCalendar.getDescription()).isEqualTo(description);

	}

	@Test
	protected void givenEmptyCalendar_whenEmptyCalendarSetYearAndGetYear_thenReturnYear() {

		emptyCalendar.setYear(year);
		assertThat(emptyCalendar.getYear()).isEqualTo(year);

	}

	@Test
	protected void givenEmptyCalendar_whenEmptyCalendarSetMarkedAsDefaultAndGetMarkedAsDefault_thenReturnMarkedAsDefault() {

		emptyCalendar.setMarkedAsDefault(markedAsDefault);
		assertThat(emptyCalendar.getMarkedAsDefault()).isEqualTo(markedAsDefault);

	}

	@Test
	protected void givenCalendar_whenEqualsWithEmptyEmployee_thenReturnFalse() {

		assertThat(calendar.equals(emptyCalendar)).isFalse();

	}

	@Test
	protected void givenCalendar_whenEqualsWithNull_thenReturnFalse() {

		assertThat(calendar.equals(null)).isFalse();

	}

	@Test
	protected void givenCalendar_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(calendar.equals(title)).isFalse();

	}

	@Test
	protected void givenCalendar_whenEqualsWithCalendar_thenReturnTrue() {

		assertThat(calendar.equals(calendar)).isTrue();

	}

}
