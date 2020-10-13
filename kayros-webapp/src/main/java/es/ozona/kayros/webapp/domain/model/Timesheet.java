
package es.ozona.kayros.webapp.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timesheet {

	private String timesheetId;

	private String employeeId;

	private LocalDate date;

	private Date startDate;
	private Date endDate;
	private String totalTime;

	private List<WorkingTimePeriod> workingTimePeriods = new ArrayList<WorkingTimePeriod>(0);

	public Timesheet() {

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

	public List<WorkingTimePeriod> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriod(List<WorkingTimePeriod> workingTimePeriods) {

		this.workingTimePeriods = workingTimePeriods;

		if (this.workingTimePeriods.size() != 0) {

			this.calculateTotalTime();

		}

	}

	public void calculateTotalTime() {

		this.startDate = this.workingTimePeriods.get(0).getStartTime();
		this.endDate = this.workingTimePeriods.get(this.workingTimePeriods.size() - 1).getFinishTime();

		Duration totalComputed = Duration.ZERO;

		for (WorkingTimePeriod wtp : this.workingTimePeriods) {

			Date end = wtp.getFinishTime() == null ? new Date() : wtp.getFinishTime();

			totalComputed =  totalComputed.plus(Duration.between(LocalDateTime.ofInstant(wtp.getStartTime().toInstant(), ZoneId.systemDefault()),
					LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault())));

		}

		if (totalComputed.getSeconds() < 60) {

			this.totalTime = "Menos de un minuto";

		} else {

			this.totalTime = new TimesheetDuration(totalComputed).toString();

		}

	}

	public Date getStartDate() {

		return startDate;

	}

	public Date getEndDate() {

		return endDate;

	}

	public String getTotalTime() {

		return totalTime;

	}

}
