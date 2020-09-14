package es.ozona.kairos.employee.application.internal.outboundservices;

import org.springframework.stereotype.Service;

import es.ozona.kairos.employee.shareddomain.model.Calendar;

@Service
public class CalendarServiceFallback implements CalendarService {

	public Calendar findDefaultCalendarByYear(Integer year) {
		return new Calendar();
	}

}
