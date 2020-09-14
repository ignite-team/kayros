package es.ozona.kairos.calendar.domain.model.valueobjects;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

@Embeddable
public class Period {

	@Column(name = "start_date")
	protected LocalDate startDate;

	@Column(name = "end_date")
	protected LocalDate endDate;

	@Column(name = "efective_end_date")
	protected LocalDate efectiveEndDate;

	/**
	 * Default constructor.
	 */
	public Period() {

	}

	/**
	 * Creates a interval between two dates.
	 * 
	 * @param startDate the start date of interval. Can't be null.
	 * @param endDate   the end date of interval. Can be null but the end date must be greater than or equal to the start date and less than or equal to the
	 *                  last day of the year.
	 */

	public Period(LocalDate startDate, LocalDate endDate) {
		super();

		Assert.notNull(startDate, "StartDate can't be null.");

		final LocalDate lastDayOfYear = getLastDayOfYear(startDate.getYear());

		if (endDate == null) {
			this.efectiveEndDate = lastDayOfYear;
		} else {
			this.efectiveEndDate = endDate;
		}

		Assert.isTrue(efectiveEndDate.isAfter(startDate.minusDays(1)) || efectiveEndDate.isBefore(lastDayOfYear.plusDays(1)),
				"The end date must be greater than or equal to the start date and less than or equal to the last day of the year.");

		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	protected LocalDate getLastDayOfYear(int year) {
		return LocalDate.of(year, 12, 31);
	}

	@Override
	public int hashCode() {
		return ObjectUtils.nullSafeHashCode(new Object[] { startDate, endDate });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof Period)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

}
