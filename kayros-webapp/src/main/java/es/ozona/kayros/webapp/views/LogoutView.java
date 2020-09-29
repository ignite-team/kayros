package es.ozona.kayros.webapp.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

//@Route(value = "logout")
//@PageTitle("Logout")
public class LogoutView extends Div implements BeforeEnterObserver {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1009594813536454000L;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		event.rerouteTo(LoginView.ROUTE);
//		UI.getCurrent().navigate(LoginView.ROUTE);
//		VaadinSession.getCurrent().getSession().invalidate();		
//		UI.getCurrent().getPage().executeJs("window.location.href=$0", "");
//		UI.getCurrent().getSession().close();
	}
}
