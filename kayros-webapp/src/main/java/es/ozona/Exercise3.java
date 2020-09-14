package es.ozona;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.Orientation;
import com.vaadin.flow.router.Route;

@Route("exercise3")
//@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class Exercise3 extends AppLayout {

	public Exercise3() {
		
		this.addToNavbar(new Button("Hola"));
		Tabs tabs = new Tabs(new Tab("Hola"), new Tab("Adios"));
		tabs.setOrientation(Orientation.VERTICAL);
		this.addToDrawer(tabs);
		
	}



}
