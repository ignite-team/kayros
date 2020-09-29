package es.ozona.kayros.webapp.views;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.internal.JsonSerializer;

import elemental.json.JsonFactory;
import elemental.json.JsonValue;
import elemental.json.impl.JreJsonFactory;

public class SpanisLoginI18n extends LoginI18n {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 7342232794186520522L;
	private static final JsonValue DEFAULT_I18N;

	static {
		try {
			final JsonFactory JSON_FACTORY = new JreJsonFactory();
			DEFAULT_I18N = JSON_FACTORY
					.parse(IOUtils.toString(LoginI18n.class.getResource("/META-INF/resources/spanishi18n.json"), StandardCharsets.UTF_8));
		} catch (IOException e) {
			throw new IllegalStateException("Cannot find the default i18n configuration");
		}
	}

	public static LoginI18n createDefault() {
		return JsonSerializer.toObject(LoginI18n.class, DEFAULT_I18N);
	}

	public SpanisLoginI18n() {

	}

}
