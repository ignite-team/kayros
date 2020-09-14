package es.ozona.kairos.calendar.application.internal.exception;

import es.ozona.micro.core.application.internal.exceptions.ApplicationException;

public class DefaultCalendarNotFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultCalendarNotFoundException(int year) {
		super("Default calendar not found for %d year. ".formatted(year));
	}

}
