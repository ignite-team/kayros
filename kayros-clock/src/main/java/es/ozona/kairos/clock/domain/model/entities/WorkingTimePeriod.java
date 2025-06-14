package es.ozona.kairos.clock.domain.model.entities;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.sun.istack.NotNull;

import es.ozona.kairos.clock.domain.model.valueobjects.FinishTime;
import es.ozona.kairos.clock.domain.model.valueobjects.StartTime;

/**
 * The class {@code WorkingTimePeriod} represents a time record of the timesheet.
 * 
 * @author Xose
 *
 * @since 1.0
 */
@Entity(name = "working_time_period")
public class WorkingTimePeriod implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private StartTime startTime;

	@Embedded
	private FinishTime finishTime;

	@NotNull
	private Boolean telecommuting;

	@NotEmpty
	@Length(min = 5, max = 75)
	@Column(name = "workplace", nullable = false, unique = false, length = 75)
	private String workplace;

	/**
	 * Default constructor.
	 */
	public WorkingTimePeriod() {

	}

	/**
	 * Create an instance of the class {@code WorkingTimePeriod} with the given arguments
	 * 
	 * @param startTime  a {@code StartTime}
	 * @param finishTime a {@code EndTime}
	 */
	public WorkingTimePeriod(StartTime startTime, Boolean telecommuting, String workplace) {

		super();
		Assert.notNull(startTime, "WorkingTimePeriod startTiem can not be null.");
		this.startTime = startTime;
		this.telecommuting = telecommuting;
		this.workplace = workplace;

	}

	/**
	 * Gets the entity Id
	 * 
	 * @return the entity Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the entity Id
	 * 
	 * @param id the Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the {@code StartTime}
	 * 
	 * @return the {@code StartTime}
	 */
	public StartTime getStartTime() {
		return startTime;
	}

	/**
	 * Sets a {@code StartTime}
	 * 
	 * @param startTime a {@code StartTime}
	 */
	public void setStartTime(StartTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the {@code FinishTime}
	 * 
	 * @return the {@code FinishTime}}
	 */
	public Optional<FinishTime> getFinishTime() {
		return Optional.ofNullable(finishTime);
	}

	/**
	 * Sets a {@code EndTime}
	 * 
	 * @param endTime a {@code EndTime}
	 */
	public void setFinishTime(FinishTime endTime) {
		this.finishTime = endTime;
	}

	public Boolean getTelecommuting() {

		return telecommuting;

	}

	public void setTelecommuting(Boolean telecommuting) {

		this.telecommuting = telecommuting;

	}

	public String getWorkplace() {

		return workplace;

	}

	public void setWorkplace(String workplace) {

		this.workplace = workplace;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { id, startTime, finishTime, telecommuting, workplace });

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof WorkingTimePeriod)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
