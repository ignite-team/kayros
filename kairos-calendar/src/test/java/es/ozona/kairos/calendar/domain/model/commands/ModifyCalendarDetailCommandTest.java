package es.ozona.kairos.calendar.domain.model.commands;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ModifyCalendarDetailCommandTest {
	private String calendarIdA = "ID-001";
	private String calendarIdB = "ID-002";
	private ModifyCalendarDetailCommand modifyCalendarCommandA = new ModifyCalendarDetailCommand(calendarIdA, "this", "description");
	private ModifyCalendarDetailCommand modifyCalendarCommandB = new ModifyCalendarDetailCommand(calendarIdA, "this", "description");
	private ModifyCalendarDetailCommand modifyCalendarCommandC = new ModifyCalendarDetailCommand(calendarIdB, "this", "description");

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new ModifyCalendarDetailCommand().hashCode()).isEqualTo(new ModifyCalendarDetailCommand().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(modifyCalendarCommandA.hashCode()).isEqualTo(modifyCalendarCommandB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(modifyCalendarCommandA.hashCode()).isNotEqualTo(modifyCalendarCommandC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(modifyCalendarCommandA).isEqualTo(modifyCalendarCommandB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(modifyCalendarCommandA).isNotEqualTo(modifyCalendarCommandC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(modifyCalendarCommandA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(modifyCalendarCommandA).isNotEqualTo("A");
	}

	@Test
	public void getterCalendarIdWorks() {
		assertThat(modifyCalendarCommandA.getCalendarId()).isEqualTo(calendarIdA);
	}

	@Test
	public void givenAnyCalendarId_whenSetCalendarIdIsCalled_thenGetCalendarIdReturnsSameValue() {
		// Given
		final String calendarId = "ID-036";
		final ModifyCalendarDetailCommand ccc = new ModifyCalendarDetailCommand();

		// When
		ccc.setCalendarId(calendarId);

		// Then
		assertThat(ccc.getCalendarId()).isEqualTo(calendarId);
	}

}
