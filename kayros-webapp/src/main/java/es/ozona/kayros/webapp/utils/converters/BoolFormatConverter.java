package es.ozona.kayros.webapp.utils.converters;

import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

public class BoolFormatConverter implements Converter<String, Boolean, Component> {

	private final String exceptionText = Labels.getLabel("exception.format.notFound");

	/**
	 * Convert Boolean to String.
	 * 
	 * @param val  Boolean to be converted
	 * @param comp associated component
	 * @param ctx  bind context for associate Binding and extra parameter (e.g. format)
	 * @return the converted String
	 */
	public String coerceToUi(Boolean val, Component comp, BindContext ctx) {
		// user sets format in annotation of binding or args when calling binder.addPropertyBinding()
		final String format = (String) ctx.getConverterArg("format");
		int boolValue = 1; // por defecto false

		if (format == null)
			throw new NullPointerException(exceptionText);

		if (val != null && val == true) { // si es true
			boolValue = 0;
		}

		final String[] boolValues = format.split(",");
		return val == null ? null : boolValues[boolValue];
	}

	/**
	 * Convert String to Boolean.
	 * 
	 * @param val  boolean in string form
	 * @param comp associated component
	 * @param ctx  bind context for associate Binding and extra parameter (e.g. format)
	 * @return the converted Boolean
	 */
	public Boolean coerceToBean(String val, Component comp, BindContext ctx) {
		final String format = (String) ctx.getConverterArg("format");
		Boolean boolValue = false; // por defecto false

		if (format == null)
			throw new NullPointerException(exceptionText);

		final String[] boolValues = format.split(",");

		if (!StringUtils.isEmpty(val) && val.equals(boolValues[0])) { // si es true
			boolValue = true;
		}

		return boolValue;
	}
}
