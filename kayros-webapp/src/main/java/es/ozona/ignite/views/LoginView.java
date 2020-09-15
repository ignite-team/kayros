package es.ozona.ignite.views;

import java.util.Optional;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "login")
@PageTitle("Login")
@CssImport("styles/views/loginview/login-view-view.css")
public class LoginView extends FlexLayout {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5914215233387846080L;

	public LoginView() {

		final LoginForm loginForm = buildLoginForm();
		loginForm.setI18n(SpanisLoginI18n.createDefault());
		loginForm.addLoginListener(event -> {
			boolean isAuthenticated = authenticate(event);

			if (isAuthenticated) {
				navigateToMainPage();
			} else {
				loginForm.setError(true);
			}
		});
		this.add(loginForm);
		this.setSizeFull();
		this.setAlignItems(Alignment.CENTER);
		this.setJustifyContentMode(JustifyContentMode.CENTER);
	}

	private void navigateToMainPage() {
		VaadinSession.getCurrent().setAttribute("userLoggedIn", true);
		Object intendedPath = VaadinSession.getCurrent().getAttribute("intendedPath");
		UI.getCurrent().navigate(Optional.ofNullable(intendedPath).map(Object::toString).orElse(""));
	}

	private boolean authenticate(LoginEvent event) {
		return event.getUsername().equals("admin") && event.getPassword().equals("admin") ? true : false;
	}

	public static LoginForm buildLoginForm() {
		final LoginForm loginForm = new LoginForm();
		return loginForm;
	}

}
