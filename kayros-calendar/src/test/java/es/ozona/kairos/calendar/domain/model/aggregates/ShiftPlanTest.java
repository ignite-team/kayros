package es.ozona.kairos.calendar.domain.model.aggregates;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftPlanCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.Workdays;

@SpringBootTest
public class ShiftPlanTest {
	private static Long id;
	private String calendarId = "id-001";
	private String shiftPlanId = "id-001";
	private LocalDate startDate = LocalDate.now();
	private LocalDate endDate = LocalDate.now();
	private ShiftPlan shiftPlanA = new ShiftPlan(new CreateShiftPlanCommand(shiftPlanId, calendarId, startDate, endDate));
	private ShiftPlan shiftPlanB = new ShiftPlan(new CreateShiftPlanCommand(shiftPlanId, calendarId, startDate, endDate));
	private ShiftPlan shiftPlanC = new ShiftPlan(new CreateShiftPlanCommand("id-002", calendarId, startDate, endDate));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new ShiftPlan().hashCode()).isEqualTo(new ShiftPlan().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(shiftPlanA.hashCode()).isEqualTo(shiftPlanB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(shiftPlanA.hashCode()).isNotEqualTo(shiftPlanC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(shiftPlanA).isEqualTo(shiftPlanB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(shiftPlanA).isNotEqualTo(shiftPlanC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(shiftPlanA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(shiftPlanA).isNotEqualTo("A");
	}

	@Test
	public void getterIdsWorks() {
		assertThat(shiftPlanA.getId()).isEqualTo(id);
	}

	@Test
	public void getterCalendarIdWorks() {
		assertThat(shiftPlanA.getCalendarId().getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void getterShiftPlanIdWorks() {
		assertThat(shiftPlanA.getShiftPlanId().getShiftPlanId()).isEqualTo(shiftPlanId);
	}

	@Test
	@Disabled // TODO: dont test now
	public void getterWorkdaysWorks() {
		final List<Workday> list = new ArrayList<>();
		list.add(new Workday());
		final Workdays workdays = new Workdays(list);
		shiftPlanA.addWorkday(new AddWorkdayCommand());
		assertThat(shiftPlanA.getWorkdays()).isEqualTo(workdays);
	}

}
