
package es.ozona.kayros.webapp.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.util.ObjectUtils;
import org.zkoss.util.resource.Labels;

public class Timesheet {

	private String timesheetId;

	private String employeeId;

	private LocalDate date;

	private Date startDate;
	private Date endDate;
	private String totalTime;

	private boolean status;

	private final String lessThanMinuteText = Labels.getLabel("general.lessThanMinute");

	private List<WorkingTimePeriod> workingTimePeriods;

	public Timesheet() {

	}

	public Timesheet(String timesheetId, String employeeId, LocalDate date, List<WorkingTimePeriod> workingTimePeriods) {

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

	public List<WorkingTimePeriod> getWorkingTimePeriods() {
		return workingTimePeriods;
	}

	public void setWorkingTimePeriods(List<WorkingTimePeriod> workingTimePeriods) {

		this.workingTimePeriods = workingTimePeriods;

		if (this.workingTimePeriods != null && this.workingTimePeriods.size() != 0) {

			this.calculateTotalTime();
			this.updateStatus();

		}

	}

	public void calculateTotalTime() {

		this.startDate = this.workingTimePeriods.get(0).getStartTime();
		this.endDate = this.workingTimePeriods.get(this.workingTimePeriods.size() - 1).getFinishTime();

		Duration totalComputed = Duration.ZERO;

		for (WorkingTimePeriod wtp : this.workingTimePeriods) {

			Date end = wtp.getFinishTime() == null ? new Date() : wtp.getFinishTime();

			totalComputed = totalComputed.plus(Duration.between(LocalDateTime.ofInstant(wtp.getStartTime().toInstant(), ZoneId.systemDefault()),
					LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault())));

		}

		if (totalComputed.getSeconds() < 60) {

			this.totalTime = lessThanMinuteText;

		} else {

			this.totalTime = new TimesheetDuration(totalComputed).toString();

		}

	}

	public void updateStatus() {

		boolean finalStatus = false;

		if (this.workingTimePeriods.get(workingTimePeriods.size() - 1).getFinishTime() == null) {

			finalStatus = true;

		}

		this.setStatus(finalStatus);

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

	public boolean getStatus() {

		return status;

	}

	public void setStatus(boolean status) {

		this.status = status;

	}

	public boolean isLatencityTimeout(int latencyTime) {

		boolean timeout = true;
		Date now = new Date();

		if (this.workingTimePeriods != null && this.workingTimePeriods.size() != 0
				&& now.getTime() - this.workingTimePeriods.get(this.workingTimePeriods.size() - 1).getStartTime().getTime() < (latencyTime * 60 * 1000)) {

			timeout = false;

		}

		return timeout;

	}

	public long getTimeToLatencyTimeout(int latencyTime) {

		long timeout = 0;
		Date now = new Date();

		if (this.workingTimePeriods != null && this.workingTimePeriods.size() != 0
				&& this.workingTimePeriods.get(this.workingTimePeriods.size() - 1).getFinishTime() == null) {

			timeout = (latencyTime * 60 * 1000) - (now.getTime() - this.workingTimePeriods.get(this.workingTimePeriods.size() - 1).getStartTime().getTime());

			if (timeout < 0) {

				timeout = 0;

			}

		}

		return timeout;

	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { timesheetId, employeeId, date, startDate, endDate, totalTime, workingTimePeriods });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Timesheet)) {

			return false;

		}

		return this.hashCode() == obj.hashCode();

	}

}
