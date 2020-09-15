package es.ozona.ignite.entities;

import java.time.ZonedDateTime;

public class WorkTime {	
	protected WorkDay workday;
	protected ZonedDateTime checkinTime;
	protected ZonedDateTime checkoutTime;

	public WorkDay getWorkday() {
		return workday;
	}
	public void setWorkday(WorkDay workday) {
		this.workday = workday;
	}
	public ZonedDateTime getCheckinTime() {
		return checkinTime;
	}
	public void setCheckinTime(ZonedDateTime checkinTime) {
		this.checkinTime = checkinTime;
	}
	public ZonedDateTime getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(ZonedDateTime checkoutTime) {
		this.checkoutTime = checkoutTime;
	}	
	
	
}
