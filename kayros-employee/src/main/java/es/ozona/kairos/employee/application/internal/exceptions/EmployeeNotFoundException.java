package es.ozona.kairos.employee.application.internal.exceptions;

import es.ozona.micro.core.application.internal.exceptions.ApplicationException;
import es.ozona.micro.core.infrastructure.message.resources.Labels;

public class EmployeeNotFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeNotFoundException(String id) {
		super(Labels.getMessage(EmployeeNotFoundException.class.getSimpleName(), id));
	}

}
