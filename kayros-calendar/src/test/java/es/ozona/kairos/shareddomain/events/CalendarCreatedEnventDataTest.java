package es.ozona.kairos.shareddomain.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.KairosCalendarApplication;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayAddedEventData;

@SpringBootTest(classes = KairosCalendarApplication.class)
public class CalendarCreatedEnventDataTest {
	private LocalDate holiday = LocalDate.now();
	private String calendarIdA = "CAL-001";
	private String calendarIdB = "CAL-002";
	private CalendarHolidayAddedEventData calendarHolidayAddedEventDataA = new CalendarHolidayAddedEventData(calendarIdA, holiday);
	private CalendarHolidayAddedEventData calendarHolidayAddedEventDataB = new CalendarHolidayAddedEventData(calendarIdA, holiday);
	private CalendarHolidayAddedEventData calendarHolidayAddedEventDataC = new CalendarHolidayAddedEventData(calendarIdB, holiday);

	@Test
	public void hashCode_2DefaultInstances_EqualsReturnsTrue() {
		assertThat(new CalendarHolidayAddedEventData().hashCode()).isEqualTo(new CalendarHolidayAddedEventData().hashCode());
	}

	@Test
	public void hashCode_DistinctInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarHolidayAddedEventDataA.hashCode()).isEqualTo(calendarHolidayAddedEventDataB.hashCode());
	}

	@Test
	public void hashCode_DistinctInstantesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarHolidayAddedEventDataA.hashCode()).isNotEqualTo(calendarHolidayAddedEventDataC.hashCode());
	}

	@Test
	public void equals_DistintInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarHolidayAddedEventDataA).isEqualTo(calendarHolidayAddedEventDataB);
	}

	@Test
	public void equals_DistinctInstancesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarHolidayAddedEventDataA).isNotEqualTo(calendarHolidayAddedEventDataC);
	}

	@Test
	public void equals_InstanceAndNull_equalsReturnsFalse() {
		assertThat(calendarHolidayAddedEventDataA).isNotEqualTo(null);
	}

	@Test
	public void equals_InstanceAndDistinctInstanceClass_equalsReturnsFalse() {
		assertThat(calendarHolidayAddedEventDataA).isNotEqualTo("A");
	}

	@Test
	public void givenAnyCalendarIdAndHoliday_WhenCallsConstructorWithThem_ThenGetCalendarIdReturnsSameValue() {
		// Given
		final LocalDate holiday = this.holiday;
		final String calendarId = calendarIdA;

		// When
		final CalendarHolidayAddedEventData calendarHolidayAddedEventData = new CalendarHolidayAddedEventData(calendarId, holiday);

		// Then
		assertThat(calendarHolidayAddedEventData.getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void givenAnyCalendarIdAndHoliday_WhenCallsConstructorWithThem_ThenGetHolidayReturnsSameValue() {
		// Given
		final LocalDate holiday = this.holiday;
		final String calendarId = calendarIdA;

		// When
		final CalendarHolidayAddedEventData calendarHolidayAddedEventData = new CalendarHolidayAddedEventData(calendarId, holiday);

		// Then
		assertThat(calendarHolidayAddedEventData.getHoliday()).isEqualTo(holiday);
	}

	@Test
	public void givenEventDataAndCalendarId_WhenSetCalendarId_ThenGetCalendarIdReturnsSameValue() {
		// Given
		final String calendarId = "CAL-003";
		final CalendarHolidayAddedEventData calendarHolidayAddedEventData = calendarHolidayAddedEventDataA;

		// When
		calendarHolidayAddedEventData.setCalendarId(calendarId);

		// Then
		assertThat(calendarHolidayAddedEventData.getCalendarId()).isEqualTo(calendarId);

	}

	@Test
	public void givenEventDataAndHoliday_WhenSetHoliday_ThenGetHolidayReturnsSameValue() {
		// Given
		final LocalDate holiday = LocalDate.now().plusDays(+1);
		final CalendarHolidayAddedEventData calendarHolidayAddedEventData = calendarHolidayAddedEventDataA;

		// When
		calendarHolidayAddedEventData.setHoliday(holiday);

		// Then
		assertThat(calendarHolidayAddedEventData.getHoliday()).isEqualTo(holiday);

	}
}
