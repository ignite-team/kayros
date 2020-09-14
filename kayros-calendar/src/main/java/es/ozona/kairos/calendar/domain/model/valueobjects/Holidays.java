package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.entities.Holiday;

@Embeddable
public class Holidays {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "calendar_id")
	private List<Holiday> holidays = Collections.emptyList();

	public Holidays() {

	}

	public Holidays(List<Holiday> holidays) {
		super();
		this.holidays = holidays;
	}

	public List<Holiday> getHolidays() {
		return Collections.unmodifiableList(holidays);
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(holidays);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Holidays)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
