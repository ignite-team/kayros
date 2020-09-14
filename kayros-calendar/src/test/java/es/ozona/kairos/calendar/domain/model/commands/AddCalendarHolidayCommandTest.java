package es.ozona.kairos.calendar.domain.model.commands;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AddCalendarHolidayCommandTest {
	private String calendarId = "ID-001";
	private LocalDate holiday = LocalDate.now();
	private AddCalendarHolidayCommand addCalendarHolidayCommandA = new AddCalendarHolidayCommand(calendarId, holiday);
	private AddCalendarHolidayCommand addCalendarHolidayCommandB = new AddCalendarHolidayCommand(calendarId, holiday);
	private AddCalendarHolidayCommand addCalendarHolidayCommandC = new AddCalendarHolidayCommand(calendarId+1, holiday);

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new AddCalendarHolidayCommand().hashCode()).isEqualTo(new AddCalendarHolidayCommand().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(addCalendarHolidayCommandA.hashCode()).isEqualTo(addCalendarHolidayCommandB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(addCalendarHolidayCommandA.hashCode()).isNotEqualTo(addCalendarHolidayCommandC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(addCalendarHolidayCommandA).isEqualTo(addCalendarHolidayCommandB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(addCalendarHolidayCommandA).isNotEqualTo(addCalendarHolidayCommandC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(addCalendarHolidayCommandA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(addCalendarHolidayCommandA).isNotEqualTo("A");
	}

	@Test
	public void givenAnyCalendarId_whenSetCalendarIdIsCalled_thenGetCalendarIdReturnsSameValue() {
		// Given
		final String calendarId = "CAL-003";
		final AddCalendarHolidayCommand ccc = new AddCalendarHolidayCommand();

		// When
		ccc.setCalendarId(calendarId);

		// Then
		assertThat(ccc.getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void givenAnyHoliday_whenSetHolidayIsCalled_thenGetHolidayReturnsSameValue() {
		// Given
		final LocalDate holiday = LocalDate.now();
		final AddCalendarHolidayCommand ccc = new AddCalendarHolidayCommand();

		// When
		ccc.setHoliday(holiday);

		// Then
		assertThat(ccc.getHoliday()).isEqualTo(holiday);
	}
}
