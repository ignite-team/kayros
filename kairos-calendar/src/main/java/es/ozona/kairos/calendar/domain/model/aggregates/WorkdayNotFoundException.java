package es.ozona.kairos.calendar.domain.model.aggregates;

import es.ozona.kairos.calendar.domain.model.valueobjects.DayOfWeek;
import es.ozona.micro.core.application.internal.exceptions.ApplicationException;

public class WorkdayNotFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkdayNotFoundException(DayOfWeek day) {
		super("Workday with id %d not found.".formatted(day.ordinal()));
	}

}
