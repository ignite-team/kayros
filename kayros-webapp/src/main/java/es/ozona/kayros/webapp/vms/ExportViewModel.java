package es.ozona.kayros.webapp.vms;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.Timesheet;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalEmployeeService;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;
import es.ozona.kayros.webapp.utils.ExportUtils;
import es.ozona.kayros.webapp.utils.SecurityAccess;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ExportViewModel {

	private final String EMPLOYEETEXT = Labels.getLabel("general.employee");
	private final String EMAILTEXT = Labels.getLabel("general.email");
	private final String DAYTEXT = Labels.getLabel("general.day");
	private final String STARTTIMETEXT = Labels.getLabel("timesheet.workingTimePeriods.headers.startDate");
	private final String GENERATEDSTARTTIMETEXT = Labels.getLabel("timesheet.workingTimePeriods.headers.generatedStartDate");
	private final String EDITEDSTARTTIMETEXT = Labels.getLabel("timesheet.workingTimePeriods.headers.editedStartDate");
	private final String FINISHTIMETEXT = Labels.getLabel("timesheet.workingTimePeriods.headers.endDate");
	private final String GENERATEDFINISHTIMETEXT = Labels.getLabel("timesheet.workingTimePeriods.headers.generatedEndDate");
	private final String EDITEDFINISHTIMETEXT = Labels.getLabel("timesheet.workingTimePeriods.headers.editedEndDate");
	private final String PARTIALTEXT = Labels.getLabel("timesheet.general.partial");
	private final String TELECOMMUTINGTEXT = Labels.getLabel("general.telecommuting");
	private final String WORKPLACETEXT = Labels.getLabel("general.workplace");

	private final String NOEMPLOYEETEXT = Labels.getLabel("exception.export.noEmployee");
	private final String FILEERRORTEXT = Labels.getLabel("exception.export.fileError");
	private final String NOWORKINGTIMEPERIODSTEXT = Labels.getLabel("exception.export.noWorkingTimePeriods");

	private final String FILENAME = Labels.getLabel("timesheet.workingTimePeriods");

	@WireVariable("externalEmployeeService")
	protected ExternalEmployeeService employeeService;

	@WireVariable("externalTimesheetService")
	protected ExternalTimesheetService timesheetService;

	private Date startDate = new Date();
	private Date endDate = new Date();
	private String fileFormat = "csv";
	private String employeeUsername = "";
	private List<Employee> employees = new ArrayList<Employee>();

	@Command("export")
	public void export() throws IOException, ParseException {

		if (!SecurityAccess.hasRole("ROLE_KAYROS_HR_MANAGER")) {

			employeeUsername = SecurityAccess.getPrincipal();

		}

		Optional<Employee> employeeOptional = employeeService.findEmployeeByUsername(employeeUsername);

		if (employeeOptional != null && employeeOptional.isPresent() == false) {

			Employee employee = employeeOptional.get();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

			Calendar starCalendar = Calendar.getInstance();
			starCalendar.setTime(startDate);

			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);

			starCalendar.add(Calendar.DATE, -1);
			endCalendar.add(Calendar.DATE, 1);

			startDate = starCalendar.getTime();
			endDate = endCalendar.getTime();

			List<Timesheet> timesheets = timesheetService.searchTimesheetsByEmployeeIdBetweenDates(formatter.format(startDate), formatter.format(endDate),
					employee.getEmployeeId());

			if (timesheets != null) {

				ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();
				ArrayList<String> headers = new ArrayList<String>();

				headers.add(EMAILTEXT);
				headers.add(EMPLOYEETEXT);
				headers.add(DAYTEXT);
				headers.add(STARTTIMETEXT);
				headers.add(GENERATEDSTARTTIMETEXT);
				headers.add(EDITEDSTARTTIMETEXT);
				headers.add(FINISHTIMETEXT);
				headers.add(GENERATEDFINISHTIMETEXT);
				headers.add(EDITEDFINISHTIMETEXT);
				headers.add(PARTIALTEXT);
				headers.add(TELECOMMUTINGTEXT);
				headers.add(WORKPLACETEXT);

				for (Timesheet timesheet : timesheets) {

					List<WorkingTimePeriod> workingTimePeriods = timesheet.getWorkingTimePeriods();

					if (workingTimePeriods != null && workingTimePeriods.size() > 0) {

						for (int x = 0; x < workingTimePeriods.size(); x++) {

							ArrayList<Object> row = new ArrayList<Object>();

							WorkingTimePeriod wtp = workingTimePeriods.get(x);

							row.add(employee.getEmail());
							row.add(employee.getUsername());
							row.add(timesheet.getDate());
							row.add(wtp.getStartTime());
							row.add(wtp.getGeneratedStartTime());
							row.add(wtp.getEditedStartTime());
							row.add(wtp.getFinishTime());
							row.add(wtp.getGeneratedFinishTime());
							row.add(wtp.getEditedFinishTime());
							row.add(wtp.getPartialDoneExport());
							row.add(wtp.getTelecommuting());
							row.add(wtp.getWorkplace());

							rows.add(row);

						}

					}

				}

				InputStream instr;

				switch (fileFormat) {
				case "csv":

					instr = ExportUtils.exportCSV(rows, headers);

					break;

				case "xlsx":

					instr = ExportUtils.exportXLSX(rows, headers);

					break;

				default:

					instr = ExportUtils.exportCSV(rows, headers);

					break;

				}

				if (instr != null) {

					Filedownload.save(instr, fileFormat, FILENAME + "." + fileFormat);
					instr.close();

				} else {

					Messagebox.show(FILEERRORTEXT, "Error", Messagebox.OK, Messagebox.ERROR);

				}

			} else {

				Messagebox.show(NOWORKINGTIMEPERIODSTEXT, "Informacion", Messagebox.OK, Messagebox.INFORMATION);

			}

		} else {

			Messagebox.show(NOEMPLOYEETEXT, "Informacion", Messagebox.OK, Messagebox.INFORMATION);

		}

	}

	public Date getStartDate() {

		return startDate;

	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;

	}

	public Date getEndDate() {

		return endDate;

	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;

	}

	public String getFileFormat() {

		return fileFormat;

	}

	public void setFileFormat(String fileType) {

		this.fileFormat = fileType;

	}

	public String getEmployeeUsername() {

		return employeeUsername;

	}

	public void setEmployeeUsername(String employeeUsername) {

		this.employeeUsername = employeeUsername;

	}

	public List<Employee> getEmployees() {

		return employees;

	}

	public void setEmployees(List<Employee> employees) {

		this.employees = employees;

	}

	@Command("searchEmployeesCommand")
	@NotifyChange({ "employees", "employeeUsername" })
	public void searchEmployeeCommand(@BindingParam("v") String value, @ContextParam(ContextType.TRIGGER_EVENT) InputEvent event) {

		setEmployeeUsername(value);

		Combobox combo = (Combobox) event.getTarget();

		if (value.length() >= 3) {

			List<Employee> employees = employeeService.findEmployeesLikeUsername(this.employeeUsername);

			if (employees.size() > 0) {

				setEmployees(employees);

				combo.setAutodrop(true);
				combo.setButtonVisible(true);

			} else {

				combo.setAutodrop(false);
				combo.setButtonVisible(false);

			}

		} else {

			combo.setAutodrop(false);
			combo.setButtonVisible(false);

		}

	}

}
