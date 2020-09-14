package es.ozona.kairos.employee.application.internal.outboundservices;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ozona.kairos.employee.domain.model.valueobjects.CalendarId;
import es.ozona.kairos.employee.shareddomain.model.Calendar;

@Service
public class ExternalCalendarService {

	@Autowired
	private CalendarService calendarService;

	public CalendarId fetchDefaultCalendar() {

		Calendar calendar = calendarService.findDefaultCalendarByYear(LocalDate.now().getYear());

		return toCalendarId(calendar);

	}

	/**
	 * Anti-corruption layer conversion
	 * 
	 * @param calendar
	 * @return Calendar to Schedule conversion
	 */
	private CalendarId toCalendarId(Calendar calendar) {

		return new CalendarId(calendar.getCalendarId());
	}

}
