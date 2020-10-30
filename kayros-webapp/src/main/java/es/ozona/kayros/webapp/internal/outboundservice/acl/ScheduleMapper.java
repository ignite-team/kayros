package es.ozona.kayros.webapp.internal.outboundservice.acl;

import java.text.SimpleDateFormat;

import es.ozona.kayros.webapp.domain.model.Schedule;
import es.ozona.kayros.webapp.shareddomain.model.ScheduleResource;

public class ScheduleMapper {

	private static String format = "dd/MM/yyyy";
	private static SimpleDateFormat formater = new SimpleDateFormat(format);

	private ScheduleMapper() {

	}

	public static Schedule mapFromResource(ScheduleResource resource) {

		try {

			final Schedule schedule = new Schedule(resource.getCalendarId(), resource.getScheduleId(), formater.parse(resource.getStartDate()),
					formater.parse(resource.getEndDate()));

			return schedule;

		} catch (Exception e) {

			return null;
		}

	}

	public static ScheduleResource mapToResource(Schedule resource) {

		final ScheduleResource schedule = new ScheduleResource(resource.getCalendarId(), resource.getScheduleId(), formater.format(resource.getStartDate()),
				formater.format(resource.getEndDate()));

		return schedule;

	}

}
