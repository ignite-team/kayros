package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.WorkingTimePeriod;
import es.ozona.kayros.webapp.shareddomain.model.WorkingTimePeriodResource;

@SpringBootTest
public class WorkingTimePeriodMapperTest {

	private ZonedDateTime startTime;
	private Date startDate;
	private ZonedDateTime finishTime;
	private Date finishDate;

	private Boolean generatedStartTime;
	private Boolean editedStartTime;
	private Boolean generatedFinishTime;
	private Boolean editedFinishTime;
	private Boolean telecommuting;
	private String workplace;

	private WorkingTimePeriod workingTimePeriod;
	private WorkingTimePeriod emptyWorkingTimePeriod;

	private WorkingTimePeriodResource workingTimePeriodResource;
	private WorkingTimePeriodResource workingTimePeriodResource2;
	private WorkingTimePeriodResource emptyWorkingTimePeriodResource;

	@BeforeEach
	public void init() {

		startTime = ZonedDateTime.now();
		finishTime = ZonedDateTime.now();

		startDate = Date.from(startTime.toInstant());
		finishDate = Date.from(finishTime.toInstant());

		generatedStartTime = false;
		editedStartTime = false;
		generatedFinishTime = false;
		editedFinishTime = false;
		telecommuting = false;
		workplace = "workplace";

		emptyWorkingTimePeriod = new WorkingTimePeriod();
		workingTimePeriod = new WorkingTimePeriod(startDate, generatedStartTime, editedStartTime, finishDate, generatedFinishTime, editedFinishTime,
				telecommuting, workplace);

		emptyWorkingTimePeriodResource = new WorkingTimePeriodResource();
		workingTimePeriodResource = new WorkingTimePeriodResource(startTime, generatedStartTime, editedStartTime, finishTime, generatedFinishTime,
				editedFinishTime, telecommuting, workplace);

		workingTimePeriodResource2 = new WorkingTimePeriodResource(startTime, generatedStartTime, editedStartTime, null, generatedFinishTime, editedFinishTime,
				telecommuting, workplace);

	}

	@Test
	protected void givenWorkingTimePeriodResource_whenWorkingTimePeriodMapperMapFromResourceEqualsWorkingTimePeriod_thenReturnTrue() {

		assertThat(WorkTimePeriodMapper.mapFromResource(workingTimePeriodResource)).isEqualTo(workingTimePeriod);

	}

	@Test
	protected void givenWorkingTimePeriodResourceWithNullFinishTime_whenWorkingTimePeriodMapperMapFromResourceEqualsWorkingTimePeriod_thenReturnFalse() {

		assertThat(WorkTimePeriodMapper.mapFromResource(workingTimePeriodResource2)).isEqualTo(workingTimePeriod);

	}

	@Test
	protected void givenEmptyWorkingTimePeriodResource_whenWorkingTimePeriodMapperMapFromResourceEqualsEmptyWorkingTimePeriod_thenReturnTrue() {

		assertThat(WorkTimePeriodMapper.mapFromResource(emptyWorkingTimePeriodResource)).isEqualTo(emptyWorkingTimePeriod);

	}

}
