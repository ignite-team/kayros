package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.Workday;
import es.ozona.kayros.webapp.shareddomain.model.WorkdayResource;

@SpringBootTest
public class WorkdayMapperTest {

	private int day;
	private String worktimeEntry;
	private String worktimeExit;
	private String breaktimeStart;
	private String breaktimeEnd;
	private String restTime;

	private WorkdayResource workdayResource;
	private Workday workday;

	@BeforeEach
	public void init() {

		day = 0;
		worktimeEntry = "worktimeEntry";
		worktimeExit = "worktimeExit";
		breaktimeStart = "breaktimeStart";
		breaktimeEnd = "breaktimeEnd";
		restTime = "restTime";

		workdayResource = new WorkdayResource(day, worktimeEntry, worktimeExit, breaktimeStart, breaktimeEnd, restTime);
		workday = new Workday(day, worktimeEntry, worktimeExit, breaktimeStart, breaktimeEnd, restTime);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanMapperMapFromResourceEqualsShiftPlan_thenReturnTrue() {

		assertThat(WorkdayMapper.mapFromResource(workdayResource).equals(workday)).isTrue();

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanMapperMapToResourceEqualsShiftPlanResource_thenReturnTrue() {

		assertThat(WorkdayMapper.mapToResource(workday).equals(workdayResource)).isTrue();

	}

}
