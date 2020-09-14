package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class BreakTime {

	@Column(name = "breaktime_start")	
	private LocalTime start;
	
	@Column(name = "breaktime_end")
	private LocalTime end;

	public BreakTime() {
		// TODO Auto-generated constructor stub
	}

	public BreakTime(LocalTime start, LocalTime stop) {
		this.start = start;
		this.end = stop;
	}

	public LocalTime getStart() {
		return start;
	}

	public LocalTime getEnd() {
		return end;
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(new Object[] { start, end });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof BreakTime)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
