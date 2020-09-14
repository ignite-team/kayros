package es.ozona.kairos.employee.application.internal.exceptions;

import es.ozona.micro.core.application.internal.exceptions.ApplicationException;
import es.ozona.micro.core.infrastructure.message.resources.Labels;

public abstract class ExternalServiceException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExternalServiceException(String url) {
		super(Labels.getMessage(ExternalServiceException.class.getSimpleName(), url));
	}

}
