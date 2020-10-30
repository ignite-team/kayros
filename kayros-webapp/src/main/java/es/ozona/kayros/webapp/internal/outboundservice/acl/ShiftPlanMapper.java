package es.ozona.kayros.webapp.internal.outboundservice.acl;

import java.text.SimpleDateFormat;

import es.ozona.kayros.webapp.domain.model.ShiftPlan;
import es.ozona.kayros.webapp.shareddomain.model.ShiftPlanResource;

public class ShiftPlanMapper {

	private static String format = "yyyy/MM/dd";
	private static SimpleDateFormat formater = new SimpleDateFormat(format);

	private ShiftPlanMapper() {

	}

	public static ShiftPlan mapFromResource(ShiftPlanResource resource) {

		try {

			final ShiftPlan shiftPlan = new ShiftPlan(resource.getShiftPlanId(), resource.getCalendarId(), formater.parse(resource.getStartDate()),
					formater.parse(resource.getEndDate()));

			return shiftPlan;

		} catch (Exception e) {

			System.err.println(e);
			return null;

		}

	}

	public static ShiftPlanResource mapToResource(ShiftPlan resource) {

		final ShiftPlanResource shiftPlan = new ShiftPlanResource(resource.getShiftPlanId(), resource.getCalendarId(), formater.format(resource.getStartDate()),
				formater.format(resource.getEndDate()));

		return shiftPlan;

	}

}
