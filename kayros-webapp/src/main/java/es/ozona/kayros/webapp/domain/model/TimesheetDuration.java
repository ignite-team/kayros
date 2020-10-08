package es.ozona.kayros.webapp.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;

public class TimesheetDuration implements TemporalAccessor {

	private static final Temporal BASE_TEMPORAL = LocalDateTime.of(0, 1, 1, 0, 0);

	private final Duration duration;
	private final Temporal temporal;

	public TimesheetDuration(Duration duration) {

		this.duration = duration;
		this.temporal = duration.addTo(BASE_TEMPORAL);

	}

	@Override
	public boolean isSupported(TemporalField field) {

		if (!temporal.isSupported(field)) {

			return false;

		}

		long value = temporal.getLong(field) - BASE_TEMPORAL.getLong(field);

		return value != 0L || field.equals(ChronoField.SECOND_OF_MINUTE);

	}

	@Override
	public long getLong(TemporalField field) {

		if (!isSupported(field)) {

			throw new UnsupportedTemporalTypeException(new StringBuilder().append(field.toString()).toString());

		}

		return temporal.getLong(field) - BASE_TEMPORAL.getLong(field);

	}

	public Duration getDuration() {

		return duration;

	}

	@Override
	public String toString() {

		return dtf.format(this);

	}

	private final DateTimeFormatter dtf = new DateTimeFormatterBuilder().optionalStart().optionalStart().appendValue(ChronoField.HOUR_OF_DAY)
			.appendLiteral(" horas ").optionalEnd().appendValue(ChronoField.MINUTE_OF_HOUR).appendLiteral(" minutos ").optionalEnd().toFormatter();

}
