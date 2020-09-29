package es.ozona.kairos.shareddomain.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.KairosCalendarApplication;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayAddedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarHolidayAddedEventData;

@SpringBootTest(classes = KairosCalendarApplication.class)
public class CalendarHolidayAddedEnventTest {
	private LocalDate holiday = LocalDate.now();
	private String calendarIdA = "CAL-001";
	private String calendarIdB = "CAL-002";
	private CalendarHolidayAddedEventData calendarCreateEventDataA = new CalendarHolidayAddedEventData(calendarIdA, holiday);
	private CalendarHolidayAddedEventData calendarCreateEventDataB = new CalendarHolidayAddedEventData(calendarIdB, holiday);
	private CalendarHolidayAddedEvent calendarHolidayAddedEventA = new CalendarHolidayAddedEvent(calendarCreateEventDataA);
	private CalendarHolidayAddedEvent calendarHolidayAddedEventB = new CalendarHolidayAddedEvent(calendarCreateEventDataA);
	private CalendarHolidayAddedEvent calendarHolidayAddedEventC = new CalendarHolidayAddedEvent(calendarCreateEventDataB);

	@Test
	public void hashCode_2DefaultInstances_EqualsReturnsTrue() {
		assertThat(new CalendarHolidayAddedEvent().hashCode()).isEqualTo(new CalendarHolidayAddedEvent().hashCode());
	}

	@Test
	public void hashCode_DistinctInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarHolidayAddedEventA.hashCode()).isEqualTo(calendarHolidayAddedEventB.hashCode());
	}

	@Test
	public void hashCode_DistinctInstantesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarHolidayAddedEventA.hashCode()).isNotEqualTo(calendarHolidayAddedEventC.hashCode());
	}

	@Test
	public void equals_DistintInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarHolidayAddedEventA).isEqualTo(calendarHolidayAddedEventB);
	}

	@Test
	public void equals_DistinctInstancesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarHolidayAddedEventA).isNotEqualTo(calendarHolidayAddedEventC);
	}

	@Test
	public void equals_InstanceAndNull_equalsReturnsFalse() {
		assertThat(calendarHolidayAddedEventA).isNotEqualTo(null);
	}

	@Test
	public void equals_InstanceAndDistinctInstanceClass_equalsReturnsFalse() {
		assertThat(calendarHolidayAddedEventA).isNotEqualTo("A");
	}

	@Test
	public void givenEventAndEventData_WhenCallConstructorWithEventData_ThenGetEventDataReturnsSameValue() {
		// Given
		final CalendarHolidayAddedEventData calendarCreatedEnCalendarHolidayAddedEventData = calendarCreateEventDataA;

		// When
		final CalendarHolidayAddedEvent calendarHolidayAddedEvent = new CalendarHolidayAddedEvent(calendarCreateEventDataA);

		// Then
		assertThat(calendarHolidayAddedEvent.getCalendarHolidayAddedEventData()).isEqualTo(calendarCreatedEnCalendarHolidayAddedEventData);
	}

	@Test
	public void givenEventData_WhenSetEventData_ThenGetEventDataReturnsSameValue() {
		// Given
		final CalendarHolidayAddedEvent calendarHolidayAddedEvent = new CalendarHolidayAddedEvent();
		final CalendarHolidayAddedEventData calendarCreatedEnCalendarHolidayAddedEventData = calendarCreateEventDataA;

		// When
		calendarHolidayAddedEvent.setCalendarHolidayAddedEventData(calendarCreateEventDataA);

		// Then
		assertThat(calendarHolidayAddedEvent.getCalendarHolidayAddedEventData()).isEqualTo(calendarCreatedEnCalendarHolidayAddedEventData);

	}
}
