package es.ozona;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route annotation to announce it in a URL as a Spring managed bean. Use the @PWA annotation make the
 * application installable on phones, tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends FlexLayout {

	/**
	 * Construct a new Vaadin view.
	 * <p>
	 * Build the initial UI state for the user accessing the application.
	 *
	 * @param service The message service. Automatically injected Spring managed bean.
	 */
	public MainView(@Autowired LoginService service) {
		LoginForm component = new LoginForm();
		this.setWidthFull();
		this.setHeightFull();
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);
		component.addLoginListener(e -> {
			boolean isAuthenticated = service.login(e.getUsername(), e.getPassword());
			if (isAuthenticated) {
				navigateToMainPage();
			} else {
				component.setError(true);
			}
		});
		add(component);
		//addClassName("centered-content");
		
		Button updateI18nButton = new Button("ES", event -> component.setI18n(createPortugueseI18n()));
		
		add(updateI18nButton);
	}

	private void navigateToMainPage() {
		Notification.show("Ha iniciado sesión con Admin.");		
	}
	
	private LoginI18n createPortugueseI18n() {
	    final LoginI18n i18n = LoginI18n.createDefault();

	    i18n.setHeader(new LoginI18n.Header());
	    i18n.getHeader().setTitle("Nome do aplicativo");
	    i18n.getHeader().setDescription("Descripción do aplicativo");
	    i18n.getForm().setUsername("Usuario");
	    i18n.getForm().setTitle("Acceso a súa conta");
	    i18n.getForm().setSubmit("Entrar");
	    i18n.getForm().setPassword("Contrasinal");
	    i18n.getForm().setForgotPassword("Esquecín o meu contrasinal.");
	    i18n.getErrorMessage().setTitle("Usuario ou contrasinal invalidos.");
	    i18n.getErrorMessage().setMessage("Confira seu usuário e senha e tente novamente.");
	    i18n.setAdditionalInformation("En caso de que necesite presentar alguna información extra para el usuario");
	    return i18n;
	}
}
