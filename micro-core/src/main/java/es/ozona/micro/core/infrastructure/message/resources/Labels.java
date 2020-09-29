package es.ozona.micro.core.infrastructure.message.resources;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Labels {

	private static MessageSource messageSource;

	public Labels(MessageSource messageSource) {
		Labels.messageSource = messageSource;
	}

	public static String getMessage(String label, Object... args) {
		if (messageSource == null) {
			throw new InitializationException("Labels has not been initialized yet.");
		}
		return messageSource.getMessage(label, args, Locale.getDefault());
	}

}
