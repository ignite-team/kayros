package es.ozona.kayros.webapp.internal.outboundservice.acl;

import es.ozona.kayros.webapp.domain.model.Calendar;
import es.ozona.kayros.webapp.shareddomain.model.CalendarResource;

public class CalendarMapper {

	private CalendarMapper() {

	}

	public static Calendar mapFromResource(CalendarResource resource) {

		final Calendar calendar = new Calendar(resource.getCalendarId(), resource.getTitle(), resource.getDescription(), resource.getYear(),
				resource.getMarkedAsDefault());

		return calendar;

	}

	public static CalendarResource mapToResource(Calendar resource) {

		final CalendarResource calendar = new CalendarResource(resource.getCalendarId(), resource.getTitle(), resource.getDescription(), resource.getYear(),
				resource.getMarkedAsDefault());

		return calendar;

	}

}
