package es.ozona.kairos.shareddomain.events;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.KairosCalendarApplication;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEvent;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEventData;

@SpringBootTest(classes = KairosCalendarApplication.class)
public class CalendarCreatedEnventTest {
	private int year = 2020;
	private String calendarIdA = "CAL-001";
	private String calendarIdB = "CAL-002";
	private CalendarCreatedEventData calendarCreateEventDataA = new CalendarCreatedEventData(calendarIdA, year);
	private CalendarCreatedEventData calendarCreateEventDataB = new CalendarCreatedEventData(calendarIdB, year);
	private CalendarCreatedEvent calendarCreatedEventA = new CalendarCreatedEvent(calendarCreateEventDataA);
	private CalendarCreatedEvent calendarCreatedEventB = new CalendarCreatedEvent(calendarCreateEventDataA);
	private CalendarCreatedEvent calendarCreatedEventC = new CalendarCreatedEvent(calendarCreateEventDataB);

	@Test
	public void hashCode_2DefaultInstances_EqualsReturnsTrue() {
		assertThat(new CalendarCreatedEvent().hashCode()).isEqualTo(new CalendarCreatedEvent().hashCode());
	}

	@Test
	public void hashCode_DistinctInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarCreatedEventA.hashCode()).isEqualTo(calendarCreatedEventB.hashCode());
	}

	@Test
	public void hashCode_DistinctInstantesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarCreatedEventA.hashCode()).isNotEqualTo(calendarCreatedEventC.hashCode());
	}

	@Test
	public void equals_DistintInstancesSameState_EqualsReturnsTrue() {
		assertThat(calendarCreatedEventA).isEqualTo(calendarCreatedEventB);
	}

	@Test
	public void equals_DistinctInstancesDistinctState_EqualsReturnsFalse() {
		assertThat(calendarCreatedEventA).isNotEqualTo(calendarCreatedEventC);
	}

	@Test
	public void equals_InstanceAndNull_equalsReturnsFalse() {
		assertThat(calendarCreatedEventA).isNotEqualTo(null);
	}

	@Test
	public void equals_InstanceAndDistinctInstanceClass_equalsReturnsFalse() {
		assertThat(calendarCreatedEventA).isNotEqualTo("A");
	}

	@Test
	public void givenEventAndEventData_WhenCallConstructorWithEventData_ThenGetEventDataReturnsSameValue() {
		// Given
		final CalendarCreatedEventData calendarCreatedEnCalendarCreatedEventData = calendarCreateEventDataA;

		// When
		final CalendarCreatedEvent calendarCreatedEvent = new CalendarCreatedEvent(calendarCreateEventDataA);

		// Then
		assertThat(calendarCreatedEvent.getCalendarCreatedEventData()).isEqualTo(calendarCreatedEnCalendarCreatedEventData);
	}

	@Test
	public void givenEventData_WhenSetEventData_ThenGetEventDataReturnsSameValue() {
		// Given
		final CalendarCreatedEvent calendarCreatedEvent = new CalendarCreatedEvent();
		final CalendarCreatedEventData calendarCreatedEnCalendarCreatedEventData = calendarCreateEventDataA;

		// When
		calendarCreatedEvent.setCalendarCreatedEventData(calendarCreateEventDataA);

		// Then
		assertThat(calendarCreatedEvent.getCalendarCreatedEventData()).isEqualTo(calendarCreatedEnCalendarCreatedEventData);

	}
}
