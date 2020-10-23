package es.ozona.kayros.webapp.utils.converters;

import java.util.Locale;

import org.zkoss.text.DateFormatInfo;

public class DateTimeFormater implements DateFormatInfo {

	@Override
	public String getDateFormat(int style, Locale locale) {
		return "dd-MM-yyyy";
	}

	@Override
	public String getTimeFormat(int style, Locale locale) {
		return "hh:mm:ss";
	}

	@Override
	public String getDateTimeFormat(int dateStyle, int timeStyle, Locale locale) {
		return "dd-MM-yyyy mm:ss";
	}

}
