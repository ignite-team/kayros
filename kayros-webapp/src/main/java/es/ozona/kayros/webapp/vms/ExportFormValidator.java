package es.ozona.kayros.webapp.vms;

import java.util.Date;
import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import es.ozona.kayros.webapp.utils.SecurityAccess;

public class ExportFormValidator extends AbstractValidator {

	private final String invalidDateText = Labels.getLabel("exception.date.invalidDate");
	private final String notTallyDatesText = Labels.getLabel("exception.date.notTally");
	private final String invalidFormatText = Labels.getLabel("exception.format.invalidFormat");
	private final String invalidEmployeeText = Labels.getLabel("exception.employee.invalidEmployee");

	public void validate(ValidationContext ctx) {

		Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

		validateStartDate(ctx, (Date) beanProps.get("startDate").getValue());
		validateEndtDate(ctx, (Date) beanProps.get("endDate").getValue());
		validateDates(ctx, (Date) beanProps.get("startDate").getValue(), (Date) beanProps.get("endDate").getValue());
		validateFileFormat(ctx, (String) beanProps.get("fileFormat").getValue());

		if (SecurityAccess.hasRole("ROLE_KAYROS_HR_MANAGER")) {

			validateEmployeeUsername(ctx, (String) beanProps.get("employeeUsername").getValue());

		}

	}

	private void validateStartDate(ValidationContext ctx, Date startDate) {

		if (startDate == null) {

			this.addInvalidMessage(ctx, "startDate", invalidDateText);

		}

	}

	private void validateEndtDate(ValidationContext ctx, Date endDate) {

		if (endDate == null) {

			this.addInvalidMessage(ctx, "endDate", invalidDateText);

		}

	}

	private void validateDates(ValidationContext ctx, Date startDate, Date endDate) {

		if (startDate != null && endDate != null) {

			if (startDate.compareTo(endDate) > 0) {

				this.addInvalidMessage(ctx, "startDate", notTallyDatesText);
				this.addInvalidMessage(ctx, "endDate", notTallyDatesText);

			}

		}

	}

	private void validateFileFormat(ValidationContext ctx, String fileFormat) {

		if (fileFormat == null || (fileFormat.equals("xlsx") == false && fileFormat.equals("csv") == false)) {

			this.addInvalidMessage(ctx, "fileFormat", invalidFormatText);

		}

	}

	private void validateEmployeeUsername(ValidationContext ctx, String employeeUsername) {

		if (employeeUsername == null || employeeUsername.length() < 3) {

			this.addInvalidMessage(ctx, "employee", invalidEmployeeText);

		}

	}

}
