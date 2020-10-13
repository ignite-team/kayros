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

import org.apache.commons.csv.CSVFormat;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import es.ozona.kayros.webapp.domain.model.Employee;
import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalEmployeeService;
import es.ozona.kayros.webapp.internal.outboundservice.ExternalTimesheetService;
import es.ozona.kayros.webapp.utils.ExportUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ExportViewModel {

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

		Optional<Employee> employee = employeeService.findEmployeeByUsername(employeeUsername);

		if (employee.isEmpty() == false) {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

			Calendar starCalendar = Calendar.getInstance();
			starCalendar.setTime(startDate);

			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(endDate);

			starCalendar.add(Calendar.DATE, -1);
			endCalendar.add(Calendar.DATE, 1);

			startDate = starCalendar.getTime();
			endDate = endCalendar.getTime();

			List<WorkingTimePeriod> workingTimePeriods = timesheetService.searchTimesheetsByEmployeeIdBetweenDates(formatter.format(startDate),
					formatter.format(endDate), employee.get().getEmployeeId());

			if (workingTimePeriods != null && workingTimePeriods.size() > 0) {

				ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();
				ArrayList<String> headers = new ArrayList<String>();

				headers.add("startTime");
				headers.add("generatedStartTime");
				headers.add("editedStartTime");
				headers.add("finishTime");
				headers.add("generatedFinishTime");
				headers.add("editedFinishTime");
				headers.add("telecommuting");
				headers.add("workplace");

				for (int x = 0; x < workingTimePeriods.size(); x++) {

					ArrayList<Object> row = new ArrayList<Object>();

					WorkingTimePeriod wtp = workingTimePeriods.get(x);

					row.add(wtp.getStartTime());
					row.add(wtp.getGeneratedStartTime());
					row.add(wtp.getEditedStartTime());
					row.add(wtp.getFinishTime());
					row.add(wtp.getGeneratedFinishTime());
					row.add(wtp.getEditedFinishTime());
					row.add(wtp.getTelecommuting());
					row.add(wtp.getWorkplace());

					rows.add(row);

				}

				InputStream instr;

				switch (fileFormat) {
				case "csv":

					instr = ExportUtils.exportCSV(CSVFormat.DEFAULT, rows, headers);

					break;

				case "xlsx":

					instr = ExportUtils.exportXLSX(rows, headers);

					break;

				default:

					instr = ExportUtils.exportCSV(CSVFormat.DEFAULT, rows, headers);

					break;

				}

				if (instr != null) {

					Filedownload.save(instr, fileFormat, "workingtimeperiods." + fileFormat);
					instr.close();

				} else {

					Messagebox.show("Error creando fichero", "Error", Messagebox.OK, Messagebox.ERROR);

				}

			} else {

				Messagebox.show("No hay registros disponibles para exportar", "Informacion", Messagebox.OK, Messagebox.INFORMATION);

			}

		} else {

			Messagebox.show("No existe el empleado", "Informacion", Messagebox.OK, Messagebox.INFORMATION);

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

		if (value.length() >= 3) {

			setEmployees(employeeService.findEmployeesLikeUsername(this.employeeUsername));

		}

	}

}
