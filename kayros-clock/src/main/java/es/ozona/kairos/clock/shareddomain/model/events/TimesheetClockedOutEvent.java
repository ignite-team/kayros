package es.ozona.kairos.clock.shareddomain.model.events;

public class TimesheetClockedOutEvent {

	private TimesheetClockedOutEventData timesheetClockOutEventData;

	public TimesheetClockedOutEvent(TimesheetClockedOutEventData timesheetClockOutEventData) {
		this.timesheetClockOutEventData = timesheetClockOutEventData;
	}

	public TimesheetClockedOutEventData getTimesheetClockOutEventData() {
		return timesheetClockOutEventData;
	}

	public void setTimesheetClockOutEventData(TimesheetClockedOutEventData timesheetClockOutEventData) {
		this.timesheetClockOutEventData = timesheetClockOutEventData;
	}

}
