package es.ozona.kairos.clock.domain.model.valueobjects;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import es.ozona.micro.core.infrastructure.message.resources.Labels;

/**
 * 
 * The class {@ FinishTime} represents a value object that encapsulates the clock out time. Clock out time can be recorded by the employee, generated or edited.
 * 
 * @author Xose
 *
 */
@Embeddable
public class FinishTime implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "finish_time", nullable = true)
	private ZonedDateTime finishTime;

	@Column(name = "generated_finish_time", nullable = true)
	private Boolean generated;

	@Column(name = "edited_finish_time", nullable = true)
	private Boolean edited;

	/**
	 * Create an instance of the class {@code FinishTime} with the given arguments
	 * 
	 */
	public FinishTime() {

	}

	/**
	 * Create an instance of the class {@code FinishTime} with the given arguments.
	 * <p>
	 * Generated value indicates that the date has been generated. The edited value indicates that the date has been corrected. When both indicators are false,
	 * the date has been clocked out by the user.
	 * </p>
	 * 
	 * @param finishTime a finish time of working time period
	 * @param generated  a generated value
	 * @param edited     a edited value
	 */
	public FinishTime(ZonedDateTime finishTime, Boolean generated, Boolean edited) {
		Assert.notNull(finishTime, Labels.getMessage("finishtime.error.finishtime.notnull"));
		Assert.notNull(generated == null, "finishtime.error.generated.notnull");
		Assert.notNull(generated == null, "finishtime.error.edited.notnull");
		Assert.isTrue((generated && edited) == false, Labels.getMessage("finishtime.error.generatedandedited.notactive", generated, edited));

		this.finishTime = finishTime;
		this.generated = generated;
		this.edited = edited;
	}

	/**
	 * Gets the finish time of period.
	 * 
	 * @return the finish time
	 */
	public ZonedDateTime getFinishTime() {
		return finishTime;
	}

	/**
	 * Gets if value has been generated.
	 * 
	 * @return true if the value has been generated else false
	 */
	public boolean isGenerated() {
		return generated;
	}

	/**
	 * Gets if value has been edited.
	 * 
	 * @return true if the value has been edited else false
	 */
	public boolean isEdited() {
		return edited;
	}

	@Override
	public int hashCode() {

		return ObjectUtils.nullSafeHashCode(new Object[] { finishTime, edited, generated });
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof TimesheetId)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}
}
