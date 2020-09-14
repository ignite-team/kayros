package es.ozona;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("exercise1")
//@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class Exercise1 extends VerticalLayout {
	public Exercise1() {	
		this.setHeightFull();
		this.setSpacing(false);
		this.setPadding(false);
		this.add(createHeader());
		this.add(createMainPanel());
		this.add(createFooter());
	}

	private HorizontalLayout createMainPanel() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.setHeightFull();
		hl.setWidthFull();		
		hl.setSpacing(false);
		hl.add(createNavPanel());
		hl.add(createContent());
		hl.getStyle().set("background-color", "pink");
		
		return hl;
	}
	
	private Div createHeader() {
		Div headerDiv = new Div();	
		headerDiv.setHeight("150px");
		headerDiv.getStyle().set("FleshShrink", "0");
		headerDiv.setWidthFull();
		headerDiv.getStyle().set("background-color", "red");
		
		return headerDiv;
	}
	
	private Div createContent() {
		Div contentDiv = new Div();
		contentDiv.setHeightFull();
		contentDiv.setWidthFull();
		contentDiv.getStyle().set("background-color", "green");
		
		return contentDiv;
	}
	
	private Div createNavPanel() {
		Div navPanel = new Div();
		navPanel.getStyle().set("FleshShrink", "0");
		navPanel.setHeightFull();
		navPanel.setWidth("150px");
		navPanel.getStyle().set("background-color", "yellow");
		
		return navPanel;
	}
	
	private Div createFooter() {
		Div footerrDiv = new Div();
		footerrDiv.getStyle().set("FleshShrink", "0");
		footerrDiv.setHeight("150px");
		footerrDiv.setWidthFull();
		footerrDiv.getStyle().set("background-color", "blue");
		
		return footerrDiv;
	}

}
