package es.ozona.kayros.webapp.utils.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

public class DateTimeFormatConverter implements Converter<String, Date, Component> {

	private final String exceptionText = Labels.getLabel("exception.format.notFound");

	/**
	 * Convert Date to String.
	 * 
	 * @param val  date to be converted
	 * @param comp associated component
	 * @param ctx  bind context for associate Binding and extra parameter (e.g. format)
	 * @return the converted String
	 */
	public String coerceToUi(Date val, Component comp, BindContext ctx) {
		// user sets format in annotation of binding or args when calling binder.addPropertyBinding()
		final String format = (String) ctx.getConverterArg("format");
		if (format == null)
			throw new NullPointerException(exceptionText);
		final Date date = (Date) val;
		return date == null ? null : new SimpleDateFormat(format).format(date);
	}

	/**
	 * Convert String to Date.
	 * 
	 * @param val  date in string form
	 * @param comp associated component
	 * @param ctx  bind context for associate Binding and extra parameter (e.g. format)
	 * @return the converted Date
	 */
	public Date coerceToBean(String val, Component comp, BindContext ctx) {
		final String format = (String) ctx.getConverterArg("format");
		if (format == null)
			throw new NullPointerException(exceptionText);
		final String date = (String) val;
		try {
			return date == null ? null : new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			throw UiException.Aide.wrap(e);
		}
	}
}
