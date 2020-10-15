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

import org.zkoss.util.resource.Labels;

public class TemporalDuration implements TemporalAccessor {
	private static final Temporal BASE_TEMPORAL = LocalDateTime.of(0, 1, 1, 0, 0);

	private final Duration duration;
	private final Temporal temporal;
	
	private final String yearsText = " " + Labels.getLabel("general.years") + " ";
	private final String monthsText = " " + Labels.getLabel("general.months") + " ";
	private final String daysText = " " + Labels.getLabel("general.days") + " ";
	private final String hoursText = " " + Labels.getLabel("general.hours") + " ";
	private final String minutesText = " " + Labels.getLabel("general.minutes") + " ";
	private final String secondsText = " " + Labels.getLabel("general.seconds") + " ";

	public TemporalDuration(Duration duration) {
		this.duration = duration;
		this.temporal = duration.addTo(BASE_TEMPORAL);
	}

	@Override
	public boolean isSupported(TemporalField field) {
		if (!temporal.isSupported(field))
			return false;
		long value = temporal.getLong(field) - BASE_TEMPORAL.getLong(field);
		return value != 0L || field.equals(ChronoField.SECOND_OF_MINUTE);
	}

	@Override
	public long getLong(TemporalField field) {
		if (!isSupported(field))
			throw new UnsupportedTemporalTypeException(new StringBuilder().append(field.toString()).toString());
		return temporal.getLong(field) - BASE_TEMPORAL.getLong(field);
	}

	public Duration getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return dtf.format(this);
	}

	private final DateTimeFormatter dtf = new DateTimeFormatterBuilder().optionalStart()// second
			.optionalStart()// minute
			.optionalStart()// hour
			.optionalStart()// day
			.optionalStart()// month
			.optionalStart()// year*/
			.appendValue(ChronoField.YEAR).appendLiteral(yearsText).optionalEnd().appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral(monthsText).optionalEnd()
			.appendValue(ChronoField.DAY_OF_MONTH).appendLiteral(daysText).optionalEnd().appendValue(ChronoField.HOUR_OF_DAY).appendLiteral(hoursText)
			.optionalEnd().appendValue(ChronoField.MINUTE_OF_HOUR).appendLiteral(minutesText).optionalEnd().appendValue(ChronoField.SECOND_OF_MINUTE)
			.appendLiteral(secondsText).optionalEnd().toFormatter();

}