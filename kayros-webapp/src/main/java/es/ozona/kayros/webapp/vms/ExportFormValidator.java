package es.ozona.kayros.webapp.vms;

import java.util.Date;
import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ExportFormValidator extends AbstractValidator {

	public void validate(ValidationContext ctx) {

		Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

		validateStartDate(ctx, (Date) beanProps.get("startDate").getValue(), (Date) beanProps.get("endDate").getValue());
		validateEndtDate(ctx, (Date) beanProps.get("startDate").getValue(), (Date) beanProps.get("endDate").getValue());
		validateFileFormat(ctx, (String) beanProps.get("fileFormat").getValue());
		validateEmployeeUsername(ctx, (String) beanProps.get("employeeUsername").getValue());

	}

	private void validateStartDate(ValidationContext ctx, Date startDate, Date endDate) {

		if (startDate == null || startDate.compareTo(endDate) > 0) {

			this.addInvalidMessage(ctx, "startDate", "Fecha invalida");

		}

	}

	private void validateEndtDate(ValidationContext ctx, Date startDate, Date endDate) {

		if (endDate == null || endDate.compareTo(startDate) < 0) {

			this.addInvalidMessage(ctx, "endDate", "Fecha invalida");

		}

	}

	private void validateFileFormat(ValidationContext ctx, String fileFormat) {

		if (fileFormat == null || (fileFormat.equals("xlsx") == false && fileFormat.equals("csv") == false)) {

			this.addInvalidMessage(ctx, "fileFormat", "Formato no valido");

		}

	}

	private void validateEmployeeUsername(ValidationContext ctx, String employeeUsername) {

		if (employeeUsername == null || employeeUsername.length() < 3) {

			this.addInvalidMessage(ctx, "employee", "Empleado no valido");

		}

	}

}
