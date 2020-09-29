package es.ozona.kairos.shareddomain.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.KairosCalendarApplication;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEventData;

@SpringBootTest(classes = KairosCalendarApplication.class)
public class CalendarHolidayAddedEnventDataTest {
	private int year = 2020;
	private String calendarIdA = "CAL-001";
	private String calendarIdB = "CAL-002";
	private CalendarCreatedEventData calendarCreatedEventDataA = new CalendarCreatedEventData(calendarIdA, year);
	private CalendarCreatedEventData calendarCreatedEventDataB = new CalendarCreatedEventData(calendarIdA, year);
	private CalendarCreatedEventData calendarCreatedEventDataC = new CalendarCreatedEventData(calendarIdB, year);

	@Test
	public void hashCode_2DefaultInstances_EqualsReturnsTrue() {
		assertThat(new CalendarCreatedEventData().hashCode()).isEqualTo(new CalendarCreatedEventData().hashCode());
	}

	@Test
	public void hashCode_DistinctInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarCreatedEventDataA.hashCode()).isEqualTo(calendarCreatedEventDataB.hashCode());
	}

	@Test
	public void hashCode_DistinctInstantesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarCreatedEventDataA.hashCode()).isNotEqualTo(calendarCreatedEventDataC.hashCode());
	}

	@Test
	public void equals_DistintInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarCreatedEventDataA).isEqualTo(calendarCreatedEventDataB);
	}

	@Test
	public void equals_DistinctInstancesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarCreatedEventDataA).isNotEqualTo(calendarCreatedEventDataC);
	}

	@Test
	public void equals_InstanceAndNull_equalsReturnsFalse() {
		assertThat(calendarCreatedEventDataA).isNotEqualTo(null);
	}

	@Test
	public void equals_InstanceAndDistinctInstanceClass_equalsReturnsFalse() {
		assertThat(calendarCreatedEventDataA).isNotEqualTo("A");
	}

	@Test
	public void givenAnyCalendarIdAndYear_WhenCallsConstructorWithThem_ThenGetCalendarIdReturnsSameValue() {
		// Given
		final int year = this.year;
		final String calendarId = calendarIdA;

		// When
		final CalendarCreatedEventData calendarCreatedEventData = new CalendarCreatedEventData(calendarId, year);

		// Then
		assertThat(calendarCreatedEventData.getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void givenAnyCalendarIdAndYear_WhenCallsConstructorWithThem_ThenGetYearReturnsSameValue() {
		// Given
		final int year = this.year;
		final String calendarId = calendarIdA;

		// When
		final CalendarCreatedEventData calendarCreatedEventData = new CalendarCreatedEventData(calendarId, year);

		// Then
		assertThat(calendarCreatedEventData.getYear()).isEqualTo(year);
	}

	@Test
	public void givenEventDataAndCalendarId_WhenSetCalendarId_ThenGetCalendarIdReturnsSameValue() {
		// Given
		final String calendarId = "CAL-003";
		final CalendarCreatedEventData calendarCreatedEventData = calendarCreatedEventDataA;

		// When
		calendarCreatedEventData.setCalendarId(calendarId);

		// Then
		assertThat(calendarCreatedEventData.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	public void givenEventDataAndYear_WhenSetYear_ThenGetYearReturnsSameValue() {
		// Given
		final int year = 2023;
		final CalendarCreatedEventData calendarCreatedEventData = calendarCreatedEventDataA;

		// When
		calendarCreatedEventData.setYear(year);

		// Then
		assertThat(calendarCreatedEventData.getYear()).isEqualTo(year);

	}
}
