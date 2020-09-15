package es.ozona.ignite.views.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import es.ozona.ignite.backend.Employee;
import es.ozona.ignite.views.MainView;

@Route(value = "tools", layout = MainView.class)
@PageTitle("Tools")
@CssImport("styles/views/form/form-view.css")
public class ToolView extends Div {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6090258111192654197L;
	private TextField firstname = new TextField();
	private TextField lastname = new TextField();
	private TextField email = new TextField();
	private TextArea notes = new TextArea();

	private DatePicker fromDate = new DatePicker();
	private DatePicker toDate = new DatePicker();

	private Button cancel = new Button("Cancel");
	private Button save = new Button("Exportar");

	public ToolView() {
		setId("tools-view");
		VerticalLayout wrapper = createWrapper();

		createTitle(wrapper);
		createFormLayout(wrapper);
		createButtonLayout(wrapper);

		// Configure Form
		Binder<Employee> binder = new Binder<>(Employee.class);

		// Bind fields. This where you'd define e.g. validation rules
		// binder.bindInstanceFields(this);

		cancel.addClickListener(e -> binder.readBean(null));
		save.addClickListener(e -> {
			Notification.show("Not implemented");
		});

		add(wrapper);
		wrapper.setMaxWidth("500px");
	}

	private void createTitle(VerticalLayout wrapper) {
		H1 h1 = new H1("Exportar horas");
		wrapper.add(h1);
	}

	private VerticalLayout createWrapper() {
		VerticalLayout wrapper = new VerticalLayout();
		wrapper.setId("wrapper");
		wrapper.setSpacing(false);
		return wrapper;
	}

	private void createFormLayout(VerticalLayout wrapper) {
		FormLayout formLayout = new FormLayout();
		addFormItem(wrapper, formLayout, fromDate, "Desde");
		addFormItem(wrapper, formLayout, toDate, "Hasta");
//        FormLayout.FormItem emailFormItem = addFormItem(wrapper, formLayout, email, "Email");
//        formLayout.setColspan(emailFormItem, 2);
//        FormLayout.FormItem notesFormItem = addFormItem(wrapper, formLayout, notes, "Notes");
//        formLayout.setColspan(notesFormItem, 2);
	}

	private void createButtonLayout(VerticalLayout wrapper) {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addClassName("button-layout");
		buttonLayout.setWidthFull();
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		// cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		// buttonLayout.add(cancel);
		buttonLayout.add(save);
		wrapper.add(buttonLayout);
	}

	private FormLayout.FormItem addFormItem(VerticalLayout wrapper, FormLayout formLayout, Component field,
			String fieldName) {
		FormLayout.FormItem formItem = formLayout.addFormItem(field, fieldName);
		wrapper.add(formLayout);
		field.getElement().getClassList().add("full-width");
		return formItem;
	}

}
