package es.ozona;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;

@Route("exercise2")
//@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class Exercise2 extends FormLayout {

	public Exercise2() {
		final TextField nombre = new TextField();
		nombre.setWidthFull();		
		this.addFormItem(nombre, "Nombre");
		
		final TextField apellido = new TextField();
		apellido.setWidthFull();
		this.addFormItem(apellido, "Apellidos");
		
		final TextField email = new TextField();
		email.setWidthFull();
		this.addFormItem(email, "Email").getElement().setAttribute("colspan", "2");		
		
		this.addFormItem(createPhoneNumberBlock(), "Telefono").getElement().setAttribute("colspan", "2");
		this.addFormItem(new PasswordField(), "Contraseña");
		this.getElement().appendChild(ElementFactory.createBr());
		this.addFormItem(new PasswordField(), "Repetir Contraseña");
		this.getElement().appendChild(ElementFactory.createBr());
	}

	private Component createPhoneNumberBlock() {
		final FlexLayout layout = new FlexLayout();
		layout.setWidthFull();
		final TextField number = new TextField(); 
		layout.add(number);
		layout.setAlignItems(Alignment.END);
		layout.add(new com.vaadin.flow.component.checkbox.Checkbox("Don't call me.",false));	
		layout.expand(number);
		return layout;
	}

}
