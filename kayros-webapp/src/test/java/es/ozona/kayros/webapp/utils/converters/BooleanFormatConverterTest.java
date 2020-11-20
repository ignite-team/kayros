package es.ozona.kayros.webapp.utils.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.zkoss.bind.BindContext;

@SpringBootTest
public class BooleanFormatConverterTest {

	private static final BoolFormatConverter converter = new BoolFormatConverter();
	private static final String TRUE = "Sí";
	private static final String FALSE = "No";
	private static final String TRUE_FALSE_FORMAT = "Sí,No";
	private static final String FORMAT_ATTRIBUTE = "format";

	@Mock
	private BindContext context;

	@Test
	protected void givenSiNoFormatValues_whenCoerceToUiTrueValue_thenReturnsSi() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(TRUE_FALSE_FORMAT);

		assertThat(converter.coerceToUi(true, null, context)).isEqualTo((Object) TRUE);

	}

	@Test
	protected void givenSiNoFormatValues_whenCoerceToUiFalseValue_thenReturnsNo() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(TRUE_FALSE_FORMAT);

		assertThat(converter.coerceToUi(false, null, context)).isEqualTo((Object) FALSE);

	}

	@Test
	protected void givenSiNoFormatValues_whenCoerceToUiNullValue_thenReturnsNull() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(TRUE_FALSE_FORMAT);

		assertThat(converter.coerceToUi(null, null, context)).isNull();

	}

	@Test
	protected void givenNullFormatValues_whenCoerceToUiTrueValue_thenReturnsNullNullPointerException() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(null);

		assertThatThrownBy(() -> {
			converter.coerceToUi(true, null, context);
		}).isInstanceOf(NullPointerException.class);

	}

	@Test
	protected void givenSiNoFormatValues_whenCoerceToBeanTrueValue_thenReturnsSi() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(TRUE_FALSE_FORMAT);

		assertThat(converter.coerceToBean(TRUE, null, context)).isTrue();

	}

	@Test
	protected void givenSiNoFormatValues_whenCoerceToBeanFalseValue_thenReturnsNo() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(TRUE_FALSE_FORMAT);

		assertThat(converter.coerceToBean(FALSE, null, context)).isFalse();

	}

	@Test
	protected void givenSiNoFormatValues_whenCoerceToBeanNullValue_thenReturnsFalse() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(TRUE_FALSE_FORMAT);

		assertThat(converter.coerceToBean(null, null, context)).isFalse();

	}

	@Test
	protected void givenNullFormatValues_whenCoerceToBeanTrueValue_thenReturnsNullNullPointerException() {

		when(context.getConverterArg(FORMAT_ATTRIBUTE)).thenReturn(null);

		assertThatThrownBy(() -> {
			converter.coerceToBean(TRUE, null, context);
		}).isInstanceOf(NullPointerException.class);

	}

}
