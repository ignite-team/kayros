package es.ozona.kayros.webapp.utils.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.zkoss.bind.BindContext;
import org.zkoss.zk.ui.UiException;

@SpringBootTest
public class DateTimeFormatConverterTest {

	private DateTimeFormatConverter converter;
	private String dateString;
	private String invalidDateString;
	private Date date;
	private String format;
	private String formatAttribute;

	@BeforeEach
	public void init() {

		dateString = "21-01-1999 14:30:15";
		invalidDateString = "";

		try {

			date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("21-01-1999 14:30:15");

		} catch (ParseException e) {

			e.printStackTrace();

		}

		converter = new DateTimeFormatConverter();
		format = "dd-MM-yyyy HH:mm:ss";
		formatAttribute = "format";

	}

	@Mock
	private BindContext context;

	@Test
	public void givenFormat_whenCoerceToUiDate_thenReturnDateString() {

		when(context.getConverterArg(formatAttribute)).thenReturn(format);

		assertThat(converter.coerceToUi(date, null, context)).isEqualTo(dateString);

	}

	@Test
	public void givenFormat_whenCoerceToUiNull_thenReturnNull() {

		when(context.getConverterArg(formatAttribute)).thenReturn(format);

		assertThat(converter.coerceToUi(null, null, context)).isNull();

	}

	@Test
	public void givenNullFormat_whenCoerceToUiDate_thenReturnNullPointerException() {

		when(context.getConverterArg(formatAttribute)).thenReturn(null);

		assertThatThrownBy(() -> {
			converter.coerceToUi(date, null, context);
		}).isInstanceOf(NullPointerException.class);

	}

	@Test
	public void givenFormat_whenCoerceToBeanDateString_thenReturnDate() {

		when(context.getConverterArg(formatAttribute)).thenReturn(format);

		assertThat(converter.coerceToBean(dateString, null, context)).isEqualTo(date);

	}

	@Test
	public void givenFormat_whenCoerceToBeanNull_thenReturnNull() {

		when(context.getConverterArg(formatAttribute)).thenReturn(format);

		assertThat(converter.coerceToBean(null, null, context)).isNull();

	}

	@Test
	public void givenNullFormat_whenCoerceToBeanDateString_thenReturnsNullPointerException() {

		when(context.getConverterArg(formatAttribute)).thenReturn(null);

		assertThatThrownBy(() -> {
			converter.coerceToBean(dateString, null, context);
		}).isInstanceOf(NullPointerException.class);

	}

	@Test
	public void givenFormat_whenCoerceToBeanInvalidDateString_thenReturnsUiException() {

		when(context.getConverterArg(formatAttribute)).thenReturn(format);

		assertThatThrownBy(() -> {
			converter.coerceToBean(invalidDateString, null, context);
		}).isInstanceOf(UiException.class);

	}

}
