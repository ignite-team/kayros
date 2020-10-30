package es.ozona.kayros.webapp.internal.outboundservice.acl;

import es.ozona.kayros.webapp.domain.model.Holiday;
import es.ozona.kayros.webapp.shareddomain.model.HolidayResource;

public class HolidayMapper {

	private HolidayMapper() {

	}

	public static Holiday mapFromResource(HolidayResource resource) {

		final Holiday holiday = new Holiday(resource.getHoliday());

		return holiday;

	}

	public static HolidayResource mapToResource(Holiday resource) {

		final HolidayResource holiday = new HolidayResource(resource.getHoliday());

		return holiday;

	}

}
