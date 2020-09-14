package es.ozona.kairos.calendar.domain.model.aggregates;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kairos.calendar.domain.model.commands.AddWorkdayCommand;
import es.ozona.kairos.calendar.domain.model.commands.CreateShiftplanCommand;
import es.ozona.kairos.calendar.domain.model.entities.Workday;
import es.ozona.kairos.calendar.domain.model.valueobjects.Workdays;

@SpringBootTest
public class ShiftplanTest {
	private static Long id;
	private String calendarId = "id-001";
	private String shiftplanId = "id-001";
	private LocalDate startDate = LocalDate.now();
	private LocalDate endDate = LocalDate.now();
	private Shiftplan shiftplanA = new Shiftplan(new CreateShiftplanCommand(shiftplanId, calendarId, startDate, endDate));
	private Shiftplan shiftplanB = new Shiftplan(new CreateShiftplanCommand(shiftplanId, calendarId, startDate, endDate));
	private Shiftplan shiftplanC = new Shiftplan(new CreateShiftplanCommand("id-002", calendarId, startDate, endDate));

	@Test
	public void hashcodeWorksOnEmptyAggregates() {
		assertThat(new Shiftplan().hashCode()).isEqualTo(new Shiftplan().hashCode());
	}

	@Test
	public void hashcodeWorksOnSameNotEmptyAggregates() {
		assertThat(shiftplanA.hashCode()).isEqualTo(shiftplanB.hashCode());
	}

	@Test
	public void hashcodeFailsOnDistinctAggregates() {
		assertThat(shiftplanA.hashCode()).isNotEqualTo(shiftplanC.hashCode());
	}

	@Test
	public void equalsWorksOnSameAggregates() {
		assertThat(shiftplanA).isEqualTo(shiftplanB);
	}

	@Test
	public void equalsFailsOnDistinctAggregates() {
		assertThat(shiftplanA).isNotEqualTo(shiftplanC);
	}

	@Test
	public void equalsFailsOnNullAggregates() {
		assertThat(shiftplanA).isNotEqualTo(null);
	}

	@Test
	public void equalsFailsOnDistinctClass() {
		assertThat(shiftplanA).isNotEqualTo("A");
	}

	@Test
	public void getterIdsWorks() {
		assertThat(shiftplanA.getId()).isEqualTo(id);
	}

	@Test
	public void getterCalendarIdWorks() {
		assertThat(shiftplanA.getCalendarId().getCalendarId()).isEqualTo(calendarId);
	}

	@Test
	public void getterShiftplanIdWorks() {
		assertThat(shiftplanA.getShiftplanId().getShiftplanId()).isEqualTo(shiftplanId);
	}

	@Test
	@Disabled // TODO: dont test now
	public void getterWorkdaysWorks() {
		final Workdays workdays = new Workdays(List.of(new Workday()));
		shiftplanA.addWorkday(new AddWorkdayCommand());
		assertThat(shiftplanA.getWorkdays()).isEqualTo(workdays);
	}

}
