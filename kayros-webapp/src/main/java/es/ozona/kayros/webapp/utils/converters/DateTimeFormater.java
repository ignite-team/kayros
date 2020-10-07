package es.ozona.kayros.webapp.utils.converters;

import java.util.Locale;

import org.zkoss.text.DateFormatInfo;

public class DateTimeFormater implements DateFormatInfo {

	@Override
	public String getDateFormat(int style, Locale locale) {
		// TODO Auto-generated method stub
		return "dd-MM-yyyy";
	}

	@Override
	public String getTimeFormat(int style, Locale locale) {
		// TODO Auto-generated method stub
		return "hh:mm:ss";
	}

	@Override
	public String getDateTimeFormat(int dateStyle, int timeStyle, Locale locale) {
		// TODO Auto-generated method stub
		return "dd-MM-yyyy mm:ss";
	}
	
}
