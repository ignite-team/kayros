package es.ozona.kayros.webapp.views;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import es.ozona.kayros.webapp.security.CustomRequestCache;

@Route(value = LoginView.ROUTE)
@PageTitle("Login")
@CssImport("styles/views/loginview/login-view-view.css")
public class LoginView extends VerticalLayout {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5914215233387846080L;

	public static final String ROUTE = "login";

	@Autowired
	public LoginView(AuthenticationManager authenticationManager, CustomRequestCache requestCache) {

		final LoginForm loginForm = buildLoginForm();
		loginForm.setI18n(SpanisLoginI18n.createDefault());

		loginForm.addLoginListener(e -> {
			try {
				final Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(e.getUsername(), e.getPassword()));
				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
					getUI().get().navigate("");
				}
			} catch (AuthenticationException ex) {
				loginForm.setError(true);
			}
		});

//		loginForm.addLoginListener(event -> {
//			boolean isAuthenticated = authenticate(event);
//
//			if (isAuthenticated) {
//				navigateToMainPage();
//			} else {
//				loginForm.setError(true);
//			}
//		});

		Button rightButton = new Button("Ozona",  new Image("img/ozona.png", "Ozona logo"));
		rightButton.setIconAfterText(false);

		Anchor anchor = new Anchor("oauth2/authorization/kayrosclient", rightButton);
		rightButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		this.add(loginForm);
		this.add(anchor);


		Image image = new Image("img/ozona.png", "Alternative text");


		
		this.add(image);
		loginForm.setForgotPasswordButtonVisible(false);
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
