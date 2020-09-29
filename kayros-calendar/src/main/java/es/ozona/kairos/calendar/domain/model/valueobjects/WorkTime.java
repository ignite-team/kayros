package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class WorkTime extends ValueObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8714341542647468798L;

	@Column(name = "worktime_entry")
	private LocalTime entry;
	
	@Column(name = "worktime_exit")
	private LocalTime exit;

	public WorkTime() {
		// TODO Auto-generated constructor stub
	}

	public WorkTime(LocalTime entry, LocalTime exit) {
		this.entry = entry;
		this.exit = exit;
	}

	public LocalTime getEntry() {
		return entry;
	}

	public LocalTime getExit() {
		return exit;
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(new Object[] { entry, exit });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof WorkTime)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
