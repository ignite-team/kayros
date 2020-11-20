
package es.ozona.kayros.webapp.shareddomain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class TimesheetResource {

	private String timesheetId;

	private String employeeId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;

	private List<WorkingTimePeriodResource> workingTimePeriods = new ArrayList<WorkingTimePeriodResource>(0);

	public TimesheetResource() {

	}

	public TimesheetResource(String timesheetId, String employeeId, LocalDate date, List<WorkingTimePeriodResource> workingTimePeriods) {

		super();
		this.timesheetId = timesheetId;
		this.employeeId = employeeId;
		this.date = date;
		this.workingTimePeriods = workingTimePeriods;

	}

	public String getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(String timesheetId) {
		this.timesheetId = timesheetId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<WorkingTimePeriodResource> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriods(List<WorkingTimePeriodResource> workingTimePeriods) {
		this.workingTimePeriods = workingTimePeriods;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { timesheetId, employeeId, date, workingTimePeriods });

	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof TimesheetResource)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
