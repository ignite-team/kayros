package es.ozona.kairos.employee.application.internal.exceptions;

import es.ozona.micro.core.infrastructure.message.resources.Labels;

public class NotFoundException extends ExternalServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String url) {
		super(Labels.getMessage(NotFoundException.class.getSimpleName(), url));
	}

}
