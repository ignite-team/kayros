package es.ozona.kairos.calendar.domain.model.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CreateCalendarCommandTest {
	private Integer year = 2020;
	private String calendarId = "ID-001";
	private CreateCalendarCommand createCalendarCommandA = new CreateCalendarCommand(calendarId, year, "title", "description", false);
	private CreateCalendarCommand createCalendarCommandB = new CreateCalendarCommand(calendarId, year, "title", "description", false);
	private CreateCalendarCommand createCalendarCommandC = new CreateCalendarCommand(calendarId, year + 1, "title", "description", false);

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new CreateCalendarCommand().hashCode()).isEqualTo(new CreateCalendarCommand().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(createCalendarCommandA.hashCode()).isEqualTo(createCalendarCommandB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(createCalendarCommandA.hashCode()).isNotEqualTo(createCalendarCommandC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(createCalendarCommandA).isEqualTo(createCalendarCommandB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(createCalendarCommandA).isNotEqualTo(createCalendarCommandC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(createCalendarCommandA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(createCalendarCommandA).isNotEqualTo("A");
	}

	@Test
	public void givenAnyCalendarId_whenSetCalendarIdIsCalled_whenGetCalendarIdReturnsSameValue() {
		// Given
		final String calendarId = "CAL-003";
		final CreateCalendarCommand ccc = new CreateCalendarCommand();

		// When
		ccc.setCalendarId(calendarId);

		// Then
		assertThat(ccc.getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void givenAnyYear_whenSetYearIsCalled_thenGetYearReturnsSameValue() {
		// Given
		final Integer year = 2021;
		final CreateCalendarCommand ccc = new CreateCalendarCommand();

		// When
		ccc.setYear(year);

		// Then
		assertThat(ccc.getYear()).isEqualTo(year);
	}

}
