package es.ozona.kairos.clock.application.internal.exceptions;

import es.ozona.micro.core.application.internal.exceptions.ApplicationException;

public class TimesheetNotFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimesheetNotFoundException() {
		super();
	}

	public TimesheetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TimesheetNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimesheetNotFoundException(String message) {
		super(message);
	}

	public TimesheetNotFoundException(Throwable cause) {
		super(cause);
	}

}
