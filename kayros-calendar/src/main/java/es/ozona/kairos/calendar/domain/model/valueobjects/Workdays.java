package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.util.ObjectUtils;

import es.ozona.kairos.calendar.domain.model.entities.Workday;

@Embeddable
public class Workdays {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "shiftplan_id")
	private List<Workday> workdays = Collections.emptyList();

	public Workdays() {

	}

	public Workdays(List<Workday> workdays) {
		super();
		this.workdays = workdays;
	}

	public List<Workday> getWorkdays() {
		return Collections.unmodifiableList(workdays);
	}

	public void addWorkday(Workday workday) {
		workdays.add(workday);
	}

	public void deleteWorkday(Workday workday) {
		workdays.remove(workday);
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(workdays);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Workdays)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
