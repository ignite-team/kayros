package es.ozona.kayros.webapp.entities;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WorkSchedule {
	Map<WeekDay, WorkDay> workDayTable = new HashMap<>(7);

	public WorkSchedule() {
		final SplittedWorkDay mondayToFriday = new SplittedWorkDay();
		final ContinuousWorkDay continuousFriday = new SplittedWorkDay();

		mondayToFriday.setStartTime(LocalTime.of(8, 0));
		mondayToFriday.setEndTime(LocalTime.of(17, 0));
		mondayToFriday.setStartBreakTime(LocalTime.of(14, 0));
		mondayToFriday.setStartBreakTime(LocalTime.of(14, 30));
		mondayToFriday.setRestTime(LocalTime.of(0, 30));
		
		continuousFriday.setStartTime(LocalTime.of(8, 0));
		continuousFriday.setEndTime(LocalTime.of(14, 0));
		continuousFriday.setRestTime(LocalTime.of(0, 30));
		
		// de lunes a viernes jornada partida
		Stream.of(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.WEDNESDAY, WeekDay.THURSDAY)
		.forEach(c -> {
			workDayTable.put(c, mondayToFriday);
		});
		
		workDayTable.put(WeekDay.FRIDAY, continuousFriday);
		
	}
}

