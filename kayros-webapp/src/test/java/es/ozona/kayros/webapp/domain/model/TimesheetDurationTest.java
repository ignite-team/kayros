package es.ozona.kayros.webapp.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimesheetDurationTest {

	private Date startDate;
	private Date endDate;

	@BeforeEach
	public void init() {

		startDate = new Date();
		endDate = new Date();

	}

	@Test
	protected void givenDurationBetweenTwoCorrectDates_whenTimesheetDuration_thenReturnEmpty() {

		Duration duration = Duration.between(LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()),
				LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));

		assertThat(new TimesheetDuration(duration).toString()).isEmpty();

	}

	@Test
	protected void given0Duration_whenTimesheetDurationIsSupportedNull_thenReturnFalse() {

		Duration duration = Duration.ZERO;

		assertThat(new TimesheetDuration(duration).isSupported(null)).isFalse();

	}

	@Test
	protected void given0Duration_whenTimesheetDurationGetDuration_thenReturnDuration() {

		Duration duration = Duration.ZERO;

		assertThat(new TimesheetDuration(duration).getDuration()).isEqualTo(duration);

	}

}
