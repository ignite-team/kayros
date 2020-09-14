package es.ozona.kairos.calendar.interfaces.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;

// @ControllerAdvice
// TODO: valorar si vale la pena usar un gestor global de excepciones.
// implicaria tranformar las excepciones de negocio en Runtime para que no obligue a capturarlas.
// tambien supone alejar la resolucion de la excepcion del codigo donde se produce.
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CalendarNotFoundException.class)
	public void springHandleNotFound(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

}
