package es.ozona.kairos.clock.shareddomain.model.events;

public class UnregisteredTimesheetClockedInEvent {

	private UnregisteredTimesheetClockedInEventData UnregisteredTimesheetClockInEventData;

	public UnregisteredTimesheetClockedInEvent(UnregisteredTimesheetClockedInEventData UnregisteredTimesheetClockInEventData) {
		this.UnregisteredTimesheetClockInEventData = UnregisteredTimesheetClockInEventData;
	}

	public UnregisteredTimesheetClockedInEventData getUnregisteredTimesheetClockInEventData() {
		return UnregisteredTimesheetClockInEventData;
	}

	public void setUnregisteredTimesheetClockInEventData(UnregisteredTimesheetClockedInEventData UnregisteredTimesheetClockInEventData) {
		this.UnregisteredTimesheetClockInEventData = UnregisteredTimesheetClockInEventData;
	}

}
