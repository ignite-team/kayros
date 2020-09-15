package es.ozona.ignite.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import es.ozona.ignite.views.layouts.ToolView;
import es.ozona.ignite.views.layouts.HourView;
import es.ozona.ignite.views.layouts.MasterDetailView;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@PWA(name = "ignite-kairos", shortName = "ignite-kairos")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout implements BeforeEnterObserver {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -4758948175318643717L;
	private final Tabs menu;

	public MainView() {
		setPrimarySection(Section.NAVBAR);
		addToNavbar(true, new DrawerToggle());
		menu = createMenuTabs();
		addToDrawer(menu);
		addToNavbar(createNavBar());
	}

	private Component createNavBar() {
		final UserDetailVO userDetail = new UserDetailVO();
		userDetail.setFirstName("Xose");
		userDetail.setLastName("Eijo");
		userDetail.setLogin("xose");
		userDetail.setCheckStatus(CheckStatus.CHECKOUT);
		return new MainHeader(userDetail);
	}

	private Tabs createMenuTabs() {
		final Tabs tabs = new Tabs();
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
		tabs.setId("tabs");
		tabs.add(getAvailableTabs());
		return tabs;
	}

	private Tab[] getAvailableTabs() {
		final List<Tab> tabs = new ArrayList<>();
		tabs.add(createTab("Horas", HourView.class));
		tabs.add(createTab("Herramientas", ToolView.class));		
		//tabs.add(createTab("Master-Detail", MasterDetailView.class));
		return tabs.toArray(new Tab[tabs.size()]);
	}

	private Tab createTab(String title, Class<? extends Component> viewClass) {
		return createTab(populateLink(new RouterLink(null, viewClass), title));
	}

	private Tab createTab(Component content) {
		final Tab tab = new Tab();
		tab.add(content);
		return tab;
	}

	private <T extends HasComponents> T populateLink(T a, String title) {
		a.add(title);
		return a;
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		selectTab();
	}

	private void selectTab() {
		String target = RouteConfiguration.forSessionScope().getUrl(getContent().getClass());
		Optional<Component> tabToSelect = menu.getChildren().filter(tab -> {
			Component child = tab.getChildren().findFirst().get();
			return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
		}).findFirst();
		tabToSelect.ifPresent(tab -> menu.setSelectedTab((Tab) tab));
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (VaadinSession.getCurrent().getAttribute("userLoggedIn") == null) {
			VaadinSession.getCurrent().setAttribute("intendedPath", event.getLocation().getPath());
			event.rerouteTo(LoginView.class);
		}
	}
 
	
}
