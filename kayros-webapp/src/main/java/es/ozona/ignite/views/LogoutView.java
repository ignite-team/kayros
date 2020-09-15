package es.ozona.ignite.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "logout")
@PageTitle("Logout")
public class LogoutView extends Div implements BeforeEnterObserver {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1009594813536454000L;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		VaadinSession.getCurrent().getSession().invalidate();		
		UI.getCurrent().getPage().executeJs("window.location.href=$0", "");
		UI.getCurrent().getSession().close();
	}
}
