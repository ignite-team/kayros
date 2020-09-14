package es.ozona.kairos.calendar.application.internal.commandservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;
import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;
import es.ozona.kairos.calendar.domain.model.commands.AddCalendarHolidayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateCalendarCommand;
import es.ozona.kairos.calendar.domain.model.entities.Holiday;
import es.ozona.kairos.calendar.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.calendar.infrastructure.repositories.CalendarRepository;

@SpringBootTest
// TODO: apply profiles for log.
public class CalendarCommandServiceImplTest {
	private Calendar calendar;
	private String calendarId;

	@MockBean
	private CalendarRepository repository;

	@Autowired
	CalendarCommandServiceImpl calendarCommandService;

	@BeforeEach
	private void init() {
		calendarId = "A not empty String.";
		calendar = new Calendar(new CreateCalendarCommand(calendarId, 2020, "title", "description", false));

		final List<Calendar> result = new ArrayList<Calendar>();

		result.add(calendar);

		when(repository.findByCalendarId(new CalendarId(calendarId))).thenReturn(result);
	}

	@Test
	public void givenValidCreateCalendarCommand_whenConstrutorICalled_thenANotEmptyStringIsReturned() {

		assertThat(calendarCommandService.createCalendar(new CreateCalendarCommand())).isNot(null);
	}

	@Test
	public void givenValidHolidayAndCalendar_whenAddCalendarHolidayIsCalled_thenHolidayIsContainedInHolidayList() throws CalendarNotFoundException {
		// Given
		final Holiday holiday = new Holiday(LocalDate.now());

		// When
		calendarCommandService.addCalendarHoliday(new AddCalendarHolidayCommand(calendarId, holiday.getHoliday()));

		// Then
		assertThat(calendar.getHolidays()).hasOnlyOneElementSatisfying(c -> c.equals(holiday));
	}

	@Test
	public void givenNotPersistedId_thenAddCalendarHolidayIsCalled_thenUnexpectedQueryResultIsThrown() throws CalendarNotFoundException {
		// Given, When & Then
		assertThatExceptionOfType(CalendarNotFoundException.class).isThrownBy(() -> {
			calendarCommandService.addCalendarHoliday(new AddCalendarHolidayCommand("NotPersistedID", null));
		}).withMessageContaining("NotPersistedID");
	}

}
