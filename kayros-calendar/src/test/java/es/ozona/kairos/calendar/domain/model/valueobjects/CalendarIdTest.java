package es.ozona.kairos.calendar.domain.model.valueobjects;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CalendarIdTest {
	private String calendarId = "2b477572-bd4a-4c28-a504-64c9486492cc";
	private CalendarId calendarIdA = new CalendarId(calendarId);
	private CalendarId calendarIdB = new CalendarId(calendarId);
	private CalendarId calendarIdC = new CalendarId("3b477572-bd4a-4c28-a504-64c9486492cc");

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new CalendarId().hashCode()).isEqualTo(new CalendarId().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(calendarIdA.hashCode()).isEqualTo(calendarIdB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(calendarIdA.hashCode()).isNotEqualTo(calendarIdC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(calendarIdA).isEqualTo(calendarIdB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(calendarIdA).isNotEqualTo(calendarIdC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(calendarIdA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(calendarIdA).isNotEqualTo("A");
	}

	@Test
	public void getterHolidaysWorks() {
		assertThat(calendarIdA.getCalendarId()).isEqualTo(calendarId);
	}

}
