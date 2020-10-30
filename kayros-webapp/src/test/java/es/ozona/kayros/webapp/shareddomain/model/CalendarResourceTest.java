package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalendarResourceTest {

	private String calendarId;
	private String title;
	private String description;
	private int year;
	private boolean markedAsDefault;

	private CalendarResource emptyCalendarResource;
	private CalendarResource calendarResource;

	@BeforeEach
	public void init() {

		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		title = "title";
		description = "description";
		year = 2020;
		markedAsDefault = false;

		emptyCalendarResource = new CalendarResource();
		calendarResource = new CalendarResource(calendarId, title, description, year, markedAsDefault);

	}

	@Test
	public void givenCalendarResource_whenCalendarResourceGetCalendarId_thenReturnCalendarId() {

		assertThat(calendarResource.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	public void givenCalendarResource_whenCalendarResourceGetTitle_thenReturnTitle() {

		assertThat(calendarResource.getTitle()).isEqualTo(title);

	}

	@Test
	public void givenCalendarResource_whenCalendarResourceGetDescription_thenReturnDescription() {

		assertThat(calendarResource.getDescription()).isEqualTo(description);

	}

	@Test
	public void givenCalendarResource_whenCalendarResourceGetYear_thenReturnYear() {

		assertThat(calendarResource.getYear()).isEqualTo(year);

	}

	@Test
	public void givenCalendarResource_whenCalendarResourceGetMarkedAsDefault_thenReturnMarkedAsDefault() {

		assertThat(calendarResource.getMarkedAsDefault()).isEqualTo(markedAsDefault);

	}

	@Test
	public void givenEmptyCalendarResource_whenEmptyCalendarResourceSetCalendarIdAndGetCalendarId_thenReturnCalendarId() {

		emptyCalendarResource.setCalendarId(calendarId);
		assertThat(emptyCalendarResource.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	public void givenEmptyCalendarResource_whenEmptyCalendarResourceSetTitleAndGetTitle_thenReturnTitle() {

		emptyCalendarResource.setTitle(title);
		assertThat(emptyCalendarResource.getTitle()).isEqualTo(title);

	}

	@Test
	public void givenEmptyCalendarResource_whenEmptyCalendarResourceSetDescriptionAndGetDescription_thenReturnDescription() {

		emptyCalendarResource.setDescription(description);
		assertThat(emptyCalendarResource.getDescription()).isEqualTo(description);

	}

	@Test
	public void givenEmptyCalendarResource_whenEmptyCalendarResourceSetYearAndGetYear_thenReturnYear() {

		emptyCalendarResource.setYear(year);
		assertThat(emptyCalendarResource.getYear()).isEqualTo(year);

	}

	@Test
	public void givenEmptyCalendarResource_whenEmptyCalendarResourceSetMarkedAsDefaultAndGetMarkedAsDefault_thenReturnMarkedAsDefault() {

		emptyCalendarResource.setMarkedAsDefault(markedAsDefault);
		assertThat(emptyCalendarResource.getMarkedAsDefault()).isEqualTo(markedAsDefault);

	}

	@Test
	public void givenCalendarResource_whenEqualsWithEmptyEmployeeResource_thenReturnFalse() {

		assertThat(calendarResource.equals(emptyCalendarResource)).isFalse();

	}

	@Test
	public void givenCalendarResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(calendarResource.equals(null)).isFalse();

	}

	@Test
	public void givenCalendarResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(calendarResource.equals(title)).isFalse();

	}

	@Test
	public void givenCalendarResource_whenEqualsWithCalendarResource_thenReturnTrue() {

		assertThat(calendarResource.equals(calendarResource)).isTrue();

	}

}
