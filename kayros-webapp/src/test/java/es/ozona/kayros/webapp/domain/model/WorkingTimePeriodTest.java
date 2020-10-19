package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkingTimePeriodTest {

	private Date startTime;
	private Boolean generatedStartTime;
	private Boolean editedStartTime;
	private Date finishTime;
	private Boolean generatedFinishTime;
	private Boolean editedFinishTime;
	private Boolean telecommuting;
	private String workplace;
	private String partialDone;

	private WorkingTimePeriod workingTimePeriod;
	private WorkingTimePeriod workingTimePeriod2;
	private WorkingTimePeriod emptyWorkingTimePeriod;

	@BeforeEach
	public void init() {

		startTime = new Date();
		generatedStartTime = false;
		editedStartTime = false;
		finishTime = new Date();
		generatedFinishTime = false;
		editedFinishTime = false;
		telecommuting = false;
		workplace = "workplace";
		partialDone = "0 Segundos ";

		emptyWorkingTimePeriod = new WorkingTimePeriod();

		workingTimePeriod = new WorkingTimePeriod(startTime, generatedStartTime, editedStartTime, finishTime, generatedFinishTime, editedFinishTime,
				telecommuting, workplace);
		workingTimePeriod2 = new WorkingTimePeriod(startTime, generatedStartTime, editedStartTime, null, generatedFinishTime, editedFinishTime, telecommuting,
				workplace);
	}

	@Test
	public void givenEmptyworkingtimeperiod_whenEmptyWorkingtimeperiodIsWorkingtimeperiod_thenReturnTrue() {

		assertThat(emptyWorkingTimePeriod.getClass()).isEqualTo(WorkingTimePeriod.class);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetStartTime_thenReturnStartTime() {

		assertThat(workingTimePeriod.getStartTime()).isEqualTo(startTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetGeneratedStartTime_thenReturnGeneratedStartTime() {

		assertThat(workingTimePeriod.getGeneratedStartTime()).isEqualTo(generatedStartTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetEditedStartTime_thenReturnEditedStartTime() {

		assertThat(workingTimePeriod.getEditedStartTime()).isEqualTo(editedStartTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetFinishTime_thenReturnFinishTime() {

		assertThat(workingTimePeriod.getFinishTime()).isEqualTo(finishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetGeneratedFinishTime_thenReturnGeneratedFinishTime() {

		assertThat(workingTimePeriod.getGeneratedFinishTime()).isEqualTo(generatedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetEditedFinishTime_thenReturnEditedFinishTime() {

		assertThat(workingTimePeriod.getEditedFinishTime()).isEqualTo(editedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetTelecommuting_thenReturnTelecommuting() {

		assertThat(workingTimePeriod.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetWorkplace_thenReturnWorkplace() {

		assertThat(workingTimePeriod.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetStartTimeGetStartTime_thenReturnStartTime() {

		workingTimePeriod.setStartTime(startTime);
		assertThat(workingTimePeriod.getStartTime()).isEqualTo(startTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetGeneratedStartTimeGetGeneratedStartTime_thenReturnGeneratedStartTime() {

		workingTimePeriod.setGeneratedStartTime(generatedStartTime);
		assertThat(workingTimePeriod.getGeneratedStartTime()).isEqualTo(generatedStartTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetEditedStartTimeGetEditedStartTime_thenReturnEditedStartTime() {

		workingTimePeriod.setEditedStartTime(editedStartTime);
		assertThat(workingTimePeriod.getEditedStartTime()).isEqualTo(editedStartTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetFinishTimeGetFinishTime_thenReturnFinishTime() {

		workingTimePeriod.setFinishTime(finishTime);
		assertThat(workingTimePeriod.getFinishTime()).isEqualTo(finishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetGeneratedFinishTimeGetGeneratedFinishTime_thenReturnGeneratedFinishTime() {

		workingTimePeriod.setGeneratedFinishTime(generatedFinishTime);
		assertThat(workingTimePeriod.getGeneratedFinishTime()).isEqualTo(generatedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetEditedFinishTimeGetEditedFinishTime_thenReturnEditedFinishTime() {

		workingTimePeriod.setEditedFinishTime(editedFinishTime);
		assertThat(workingTimePeriod.getEditedFinishTime()).isEqualTo(editedFinishTime);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetTelecommutingAndGetTelecommuting_thenReturnTelecommuting() {

		workingTimePeriod.setTelecommuting(telecommuting);
		assertThat(workingTimePeriod.getTelecommuting()).isEqualTo(telecommuting);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodSetWorkplaceAndGetWorkplace_thenReturnWorkplace() {

		workingTimePeriod.setWorkplace(workplace);
		assertThat(workingTimePeriod.getWorkplace()).isEqualTo(workplace);

	}

	@Test
	public void givenWorkingtimeperiod_whenWorkingtimeperiodGetPartialD0ne_thenReturnPartialDone() {

		assertThat(workingTimePeriod.getPartialDone()).isEqualTo(partialDone);

	}

	@Test
	public void givenWorkingtimeperiod2_whenWorkingtimeperiod2GetPartialDone_thenReturnPartialDone() {

		assertThat(workingTimePeriod2.getPartialDone()).isEqualTo(partialDone);

	}

	@Test
	public void givenWorkingTimePeriod_whenEqualsWithEmptyWorkingTimePeriod_thenReturnFalse() {

		assertThat(workingTimePeriod.equals(emptyWorkingTimePeriod)).isFalse();

	}

	@Test
	public void givenWorkingTimePeriod_whenEqualsWithNull_thenReturnFalse() {

		assertThat(workingTimePeriod.equals(null)).isFalse();

	}

	@Test
	public void givenWorkingTimePeriod_whenEqualsWithOtherClass_thenReturnFalse() {

		assertThat(workingTimePeriod.equals(workplace)).isFalse();

	}

	@Test
	public void givenWorkingTimePeriod_whenEqualsWithWorkingTimePeriod_thenReturnTrue() {

		assertThat(workingTimePeriod.equals(workingTimePeriod)).isTrue();

	}

}
