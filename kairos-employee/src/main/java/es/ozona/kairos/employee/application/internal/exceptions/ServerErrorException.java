package es.ozona.kairos.employee.application.internal.exceptions;

import es.ozona.micro.core.infrastructure.message.resources.Labels;

public class ServerErrorException extends ExternalServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServerErrorException(String url) {
		super(Labels.getMessage(ServerErrorException.class.getSimpleName(), url));
	}

}
