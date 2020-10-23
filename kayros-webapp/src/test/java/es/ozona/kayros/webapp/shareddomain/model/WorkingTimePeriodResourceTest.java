package es.ozona.kayros.webapp.shareddomain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkingTimePeriodResourceTest {

	private ZonedDateTime startTime;
	private Boolean generatedStartTime;
	private Boolean editedStartTime;
	private ZonedDateTime finishTime;
	private Boolean generatedFinishTime;
	private Boolean editedFinishTime;
	private Boolean telecommuting;
	private String workplace;

	private WorkingTimePeriodResource workingTimePeriodResource;
	private WorkingTimePeriodResource emptyWorkingTimePeriodResource;

	@BeforeEach
	public void init() {

		startTime = ZonedDateTime.now();
		generatedStartTime = false;
		editedStartTime = false;
		finishTime = ZonedDateTime.now();
		generatedFinishTime = false;
		editedFinishTime = false;
		telecommuting = false;
		workplace = "workplace";

		emptyWorkingTimePeriodResource = new WorkingTimePeriodResource();

		workingTimePeriodResource = new WorkingTimePeriodResource(startTime, generatedStartTime, editedStartTime, finishTime, generatedFinishTime,
				editedFinishTime, telecommuting, workplace);

	}

	@Test
	public void givenEmptyworkingtimeperiodResource_whenEmptyWorkingtimeperiodResourceIsWorkingtimeperiodResource_thenReturnTrue() {

		assertThat(emptyWorkingTimePeriodResource.getClass()).isEqualTo(WorkingTimePeriodResource.class);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetStartTime_thenReturnStartTime() {

		assertThat(workingTimePeriodResource.getStartTime()).isEqualTo(startTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetGeneratedStartTime_thenReturnGeneratedStartTime() {

		assertThat(workingTimePeriodResource.getGeneratedStartTime()).isEqualTo(generatedStartTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetEditedStartTime_thenReturnEditedStartTime() {

		assertThat(workingTimePeriodResource.getEditedStartTime()).isEqualTo(editedStartTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetFinishTime_thenReturnFinishTime() {

		assertThat(workingTimePeriodResource.getFinishTime()).isEqualTo(finishTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetGeneratedFinishTime_thenReturnGeneratedFinishTime() {

		assertThat(workingTimePeriodResource.getGeneratedFinishTime()).isEqualTo(generatedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetEditedFinishTime_thenReturnEditedFinishTime() {

		assertThat(workingTimePeriodResource.getEditedFinishTime()).isEqualTo(editedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodGetTelecommuting_thenReturnTelecommuting() {

		assertThat(workingTimePeriodResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceGetWorkplace_thenReturnWorkplace() {

		assertThat(workingTimePeriodResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetStartTimeGetStartTime_thenReturnStartTime() {

		workingTimePeriodResource.setStartTime(startTime);
		assertThat(workingTimePeriodResource.getStartTime()).isEqualTo(startTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetGeneratedStartTimeGetGeneratedStartTime_thenReturnGeneratedStartTime() {

		workingTimePeriodResource.setGeneratedStartTime(generatedStartTime);
		assertThat(workingTimePeriodResource.getGeneratedStartTime()).isEqualTo(generatedStartTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetEditedStartTimeGetEditedStartTime_thenReturnEditedStartTime() {

		workingTimePeriodResource.setEditedStartTime(editedStartTime);
		assertThat(workingTimePeriodResource.getEditedStartTime()).isEqualTo(editedStartTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetFinishTimeGetFinishTime_thenReturnFinishTime() {

		workingTimePeriodResource.setFinishTime(finishTime);
		assertThat(workingTimePeriodResource.getFinishTime()).isEqualTo(finishTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetGeneratedFinishTimeGetGeneratedFinishTime_thenReturnGeneratedFinishTime() {

		workingTimePeriodResource.setGeneratedFinishTime(generatedFinishTime);
		assertThat(workingTimePeriodResource.getGeneratedFinishTime()).isEqualTo(generatedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetEditedFinishTimeGetEditedFinishTime_thenReturnEditedFinishTime() {

		workingTimePeriodResource.setEditedFinishTime(editedFinishTime);
		assertThat(workingTimePeriodResource.getEditedFinishTime()).isEqualTo(editedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		workingTimePeriodResource.setTelecommuting(telecommuting);
		assertThat(workingTimePeriodResource.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenWorkingtimeperiodResource_whenWorkingtimeperiodResourceSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		workingTimePeriodResource.setWorkplace(workplace);
		assertThat(workingTimePeriodResource.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenWorkingTimePeriodResource_whenEqualsWithEmptyWorkingTimePeriodResource_thenReturnFalse() {

		assertThat(workingTimePeriodResource.equals(emptyWorkingTimePeriodResource)).isFalse();

	}

	@Test
	public void givenWorkingTimePeriodResource_whenEqualsWithNull_thenReturnFalse() {

		assertThat(workingTimePeriodResource.equals(null)).isFalse();

	}

	@Test
	public void givenWorkingTimePeriodResource_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(workingTimePeriodResource.equals(workplace)).isFalse();

	}

	@Test
	public void givenWorkingTimePeriodResource_whenEqualsWithWorkingTimePeriodResource_thenReturnTrue() {

		assertThat(workingTimePeriodResource.equals(workingTimePeriodResource)).isTrue();

	}
}
