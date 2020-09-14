package es.ozona.kairos.calendar.domain.model.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DeleteCalendarCommandTest {
	private String calendarIdA = "ID-001";
	private String calendarIdB = "ID-002";
	private DeleteCalendarCommand removeCalendarCommandA = new DeleteCalendarCommand(calendarIdA);
	private DeleteCalendarCommand removeCalendarCommandB = new DeleteCalendarCommand(calendarIdA);
	private DeleteCalendarCommand removeCalendarCommandC = new DeleteCalendarCommand(calendarIdB);

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new DeleteCalendarCommand().hashCode()).isEqualTo(new DeleteCalendarCommand().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(removeCalendarCommandA.hashCode()).isEqualTo(removeCalendarCommandB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(removeCalendarCommandA.hashCode()).isNotEqualTo(removeCalendarCommandC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(removeCalendarCommandA).isEqualTo(removeCalendarCommandB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(removeCalendarCommandA).isNotEqualTo(removeCalendarCommandC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(removeCalendarCommandA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(removeCalendarCommandA).isNotEqualTo("A");
	}

	@Test
	public void getterHolidaysWorks() {
		assertThat(removeCalendarCommandA.getCalendarId()).isEqualTo(calendarIdA);
	}

	@Test
	public void givenAnyCalendarId_whenSetCalendarIdIsCalled_thenGetCalendarIdReturnsSameValue() {
		// Given
		final String calendarId = "ID-036";
		final DeleteCalendarCommand ccc = new DeleteCalendarCommand();

		// When
		ccc.setCalendarId(calendarId);

		// Then
		assertThat(ccc.getCalendarId()).isEqualTo(calendarId);
	}

}
