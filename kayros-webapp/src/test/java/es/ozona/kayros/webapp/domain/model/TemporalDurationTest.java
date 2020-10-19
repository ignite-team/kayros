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
public class TemporalDurationTest {

	private Date startDate;
	private Date endDate;
	private String durationText;

	@BeforeEach
	public void init() {

		startDate = new Date();
		endDate = new Date();
		durationText = "0 Segundos ";

	}

	@Test
	public void givenDurationBetweenTwoCorrectDates_whenTemporalDuration_thenReturnEmpty() {

		Duration duration = Duration.between(LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()),
				LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));

		assertThat(new TemporalDuration(duration).toString()).isEqualTo(durationText);

	}

	@Test
	public void given0Duration_whenTemporalDurationIsSupportedNull_thenReturnFalse() {

		Duration duration = Duration.ZERO;

		assertThat(new TemporalDuration(duration).isSupported(null)).isEqualTo(false);

	}

	@Test
	public void given0Duration_whenTemporalDurationGetDuration_thenReturnDuration() {

		Duration duration = Duration.ZERO;

		assertThat(new TemporalDuration(duration).getDuration().equals(duration)).isEqualTo(true);

	}

}
