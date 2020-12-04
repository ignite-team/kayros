package es.ozona.kairos.calendar.domain.model.aggregates;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.shareddomain.model.events.CalendarCreatedEvent;

@SpringBootTest
public class CalendarTest {
	private Long id;
	private CalendarId calendarId = new CalendarId("2b477572-bd4a-4c28-a504-64c9486492cc");
	private int year = 2020;
	private Calendar calendarA = new Calendar(new CreateCalendarCommand(calendarId.getCalendarId(), year, "title", "description", false));
	private Calendar calendarB = new Calendar(new CreateCalendarCommand(calendarId.getCalendarId(), year, "title", "description", false));
	private Calendar calendarC = new Calendar(new CreateCalendarCommand(calendarId.getCalendarId(), 2021, "title", "description", false));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Calendar().hashCode()).isEqualTo(new Calendar().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(calendarA.hashCode()).isEqualTo(calendarB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(calendarA.hashCode()).isNotEqualTo(calendarC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(calendarA).isEqualTo(calendarB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(calendarA).isNotEqualTo(calendarC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(calendarA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(calendarA).isNotEqualTo("A");
	}

	@Test
	public void getterIdsWorks() {
		assertThat(calendarA.getId()).isEqualTo(id);
	}

	@Test
	public void getterIdCalendarWorks() {
		assertThat(calendarA.getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void getterYearWorks() {
		assertThat(calendarA.getYear()).isEqualTo(year);
	}

	@Test
	public void getterHolidaysWorks() {
		final Calendar calendar = new Calendar();
		final LocalDate holiday = LocalDate.now();
		calendar.addCalendarHoliday(new AddCalendarHolidayCommand(null, holiday));

		Condition<Holiday> holidayAdded = new Condition<Holiday>(m -> m.getHoliday().equals(holiday), "holiday Added");

		assertThat(calendar.getHolidays()).haveExactly(1, holidayAdded);
	}

	@Test
	public void givenAnyCreateComandEvent_WhenCallsConstructor_ThenADomainEventIsCreated() throws IllegalAccessException {
		final Calendar calendar = new Calendar(new CreateCalendarCommand(calendarId.getCalendarId(), year, "title", "description", false));
		@SuppressWarnings("unchecked")
		final List<CalendarCreatedEvent> events = (List<CalendarCreatedEvent>) FieldUtils.readField(calendar, "domainEvents", true);

		assertThat(events).hasOnlyElementsOfType(CalendarCreatedEvent.class);
	}
}
