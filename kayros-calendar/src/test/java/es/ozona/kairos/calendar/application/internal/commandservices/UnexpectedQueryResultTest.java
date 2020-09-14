package es.ozona.kairos.calendar.application.internal.commandservices;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.application.internal.exception.CalendarNotFoundException;

@SpringBootTest
public class UnexpectedQueryResultTest {

	@Test
	public void givenNoParams_whenThrowIsCalled_thenExceptionisThrown() {
		// Given, When & Then
		assertThatExceptionOfType(CalendarNotFoundException.class).isThrownBy(() -> {
			throw new CalendarNotFoundException();
		}).withMessage(null);
	}

	@Test
	public void givenMessage_whenThrowIsCalled_thenExceptionisThrown() {
		// Given
		final String theMessage = "The message";

		// When & Then
		assertThatExceptionOfType(CalendarNotFoundException.class).isThrownBy(() -> {
			throw new CalendarNotFoundException(theMessage);
		}).withMessage(theMessage);
	}

	@Test
	public void givenAnyThrowable_whenThrowIsCalled_thenExceptionisThrown() {
		// Given
		final IOException ioException = new IOException();

		// When & Then
		assertThatExceptionOfType(CalendarNotFoundException.class).isThrownBy(() -> {
			throw new CalendarNotFoundException(ioException);
		}).withCause(ioException);
	}

	@Test
	public void givenAnyThrowableAndMessage_whenThrowIsCalled_thenExceptionisThrown() {
		// Given
		final IOException ioException = new IOException();
		final String theMessage = "The message";

		// When & Then
		assertThatExceptionOfType(CalendarNotFoundException.class).isThrownBy(() -> {
			throw new CalendarNotFoundException(theMessage, ioException);
		}).withCause(ioException).withMessage(theMessage);
	}

	@Test
	public void givenAnyThrowableAndMessageAndSupressionAndWriteable_whenThrowIsCalled_thenExceptionisThrown() {
		// Given
		final IOException ioException = new IOException();
		final String theMessage = "The message";
		final boolean enableSuppression = true;
		final boolean writableStackTrace = true;

		// When & Then
		assertThatExceptionOfType(CalendarNotFoundException.class).isThrownBy(() -> {
			throw new CalendarNotFoundException(theMessage, ioException, enableSuppression, writableStackTrace);
		}).withCause(ioException).withMessage(theMessage); // don´t check supression and writable
	}

}
