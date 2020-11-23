package es.ozona.kayros.webapp.utils.converters;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DateTimeFormaterTest {

	private DateTimeFormater formater;
	private String dateFormat;
	private String timeFormat;
	private String dateTimeFormat;

	@BeforeEach
	public void init() {

		formater = new DateTimeFormater();
		dateFormat = "dd-MM-yyyy";
		timeFormat = "hh:mm:ss";
		dateTimeFormat = "dd-MM-yyyy mm:ss";

	}

	@Test
	protected void givenFormater_whenFormaterGetDate_thenReturnDateFormat() {

		assertThat(formater.getDateFormat(0, Locale.ENGLISH)).isEqualTo(dateFormat);

	}

	@Test
	protected void givenFormater_whenFormaterGetTime_thenReturnTimeFormat() {

		assertThat(formater.getTimeFormat(0, Locale.ENGLISH)).isEqualTo(timeFormat);

	}

	@Test
	protected void givenFormater_whenFormaterGetDateTime_thenReturnDateTimeFormat() {

		assertThat(formater.getDateTimeFormat(0, 0, Locale.ENGLISH)).isEqualTo(dateTimeFormat);

	}

}
