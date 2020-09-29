package es.ozona.kairos.clock.shareddomain.model.events;

public class TimesheetClockedInEvent {

	private TimesheetClockedInEventData timesheetClockInEventData;

	public TimesheetClockedInEvent(TimesheetClockedInEventData timesheetClockInEventData) {
		this.timesheetClockInEventData = timesheetClockInEventData;
	}

	public TimesheetClockedInEventData getTimesheetClockInEventData() {
		return timesheetClockInEventData;
	}

	public void setTimesheetClockInEventData(TimesheetClockedInEventData timesheetClockInEventData) {
		this.timesheetClockInEventData = timesheetClockInEventData;
	}

}
