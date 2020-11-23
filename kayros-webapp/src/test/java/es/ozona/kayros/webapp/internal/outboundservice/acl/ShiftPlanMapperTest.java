package es.ozona.kayros.webapp.internal.outboundservice.acl;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import es.ozona.kayros.webapp.domain.model.ShiftPlan;
import es.ozona.kayros.webapp.shareddomain.model.ShiftPlanResource;

@SpringBootTest
public class ShiftPlanMapperTest {

	private String shiftPlanId;
	private String calendarId;
	private Date startDate;
	private Date endDate;
	private String startDateString;
	private String endDateString;

	private ShiftPlanResource shiftPlanResource;

	private ShiftPlan shiftPlan;

	private static String pattern = "yyyy/MM/dd";
	private static SimpleDateFormat formater = new SimpleDateFormat(pattern);

	@BeforeEach
	public void init() throws java.text.ParseException {

		shiftPlanId = "2B477572-BD4A-4C28-A504-64C948649211";
		calendarId = "2B477572-BD4A-4C28-A504-64C9486492CC";
		startDate = formater.parse("1970/01/01");
		endDate = formater.parse("1970/01/01");
		startDateString = "1970/01/01";
		endDateString = "1970/01/01";

		shiftPlanResource = new ShiftPlanResource(shiftPlanId, calendarId, startDateString, endDateString);
		shiftPlan = new ShiftPlan(shiftPlanId, calendarId, startDate, endDate);

	}

	@Test
	protected void givenShiftPlanResource_whenShiftPlanMapperMapFromResourceEqualsShiftPlan_thenReturnTrue() {

		assertThat(ShiftPlanMapper.mapFromResource(shiftPlanResource)).isEqualTo(shiftPlan);

	}

	@Test
	protected void givenShiftPlan_whenShiftPlanMapperMapToResourceEqualsShiftPlanResource_thenReturnTrue() {

		assertThat(ShiftPlanMapper.mapToResource(shiftPlan)).isEqualTo(shiftPlanResource);

	}

}