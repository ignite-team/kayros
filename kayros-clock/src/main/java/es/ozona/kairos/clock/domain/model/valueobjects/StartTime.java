package es.ozona.kairos.clock.domain.model.valueobjects;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import es.ozona.micro.core.infrastructure.message.resources.Labels;

/**
 * 
 * The class {@ StartTime} represents a value object that encapsulates the clock in time. Clock in time can be recorded by the employee, generated or edited.
 * 
 * @author Xose
 *
 */
@Embeddable
public class StartTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "start_time", nullable = false)
	private ZonedDateTime time;

	@NotNull
	@Column(name = "generated_start_time", nullable = false)
	private Boolean generated;

	@NotNull
	@Column(name = "edited_start_time", nullable = false)
	private Boolean edited;

	/**
	 * Creates a default instance of class {@code StartTime}
	 */
	public StartTime() {

	}

	/**
	 * Create an instance of the class {@code StartTime} with the given arguments
	 * 
	 * @param startTime
	 */
	public StartTime(ZonedDateTime startTime, Boolean generated, Boolean edited) {
		Assert.notNull(startTime, Labels.getMessage("starttime.error.starttime.notnull"));
		Assert.notNull(generated == null, "starttime.error.generated.notnull");
		Assert.notNull(generated == null, "starttime.error.edited.notnull");
		Assert.isTrue((generated && edited) == false, Labels.getMessage("starttime.error.generatedandedited.notactive", generated, edited));

		this.time = startTime;
		this.generated = generated;
		this.edited = edited;
	}

	/**
	 * Gets the start time
	 * 
	 * @return the start time
	 */
	public ZonedDateTime getStartTime() {
		return time;
	}

	/**
	 * 
	 * @return true if starttime was generated
	 */
	public boolean isGenerated() {
		return generated;
	}

	/**
	 * 
	 * @return true if startime was edited
	 */
	public boolean isEdited() {
		return edited;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { time, edited, generated });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof TimesheetId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
	
	

}
