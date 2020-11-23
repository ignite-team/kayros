package es.ozona.kairos.calendar.integration;

import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.ozona.kairos.calendar.domain.model.aggregates.Calendar;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CalendarRestApiTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int randomServerPort;

	private JSONObject calendar2020;
	private JSONObject calendar2019;

	private HttpHeaders headers;

	@Value("${kairosCalendarApplication.service.calendar.url}")
	private String baseUrl;

	@Value("${kairosCalendarApplication.service.calendar.api.create}")
	private String createPath;

	@Value("${kairosCalendarApplication.service.calendar.api.find}")
	private String findPath;

	@Value("${kairosCalendarApplication.service.calendar.api.findByYear}")
	private String findByYearPath;

	@Value("${kairosCalendarApplication.service.calendar.api.findAll}")
	private String findAll;

	@Value("${kairosCalendarApplication.service.calendar.api.holidayCreate}")
	private String holidayCreate;

	@BeforeEach
	public void init() throws JSONException {
		restTemplate = new TestRestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("X-COM-PERSIST", "true");

		calendar2020 = new JSONObject();
		calendar2020.put("year", "2020");

		calendar2019 = new JSONObject();
		calendar2019.put("year", "2019");
	}

	public void find() throws URISyntaxException, JsonMappingException, JsonProcessingException, JSONException {

		String calendarId = post(createPath, calendar2019, randomServerPort);
		ResponseEntity<Calendar> result;
		ResponseEntity<Calendar[]> listResult;

		post(createPath, calendar2020, randomServerPort);

		result = this.restTemplate.getForEntity(baseUrl + findPath, Calendar.class, randomServerPort, calendarId);

		listResult = this.restTemplate.getForEntity(baseUrl + findByYearPath, Calendar[].class, randomServerPort, 2020);

		listResult = this.restTemplate.getForEntity(baseUrl + findAll, Calendar[].class, randomServerPort);

		JSONObject dayJson = new JSONObject();
		dayJson.put("holiday", ZonedDateTime.now(ZoneId.of("UTC")).toString());

		//post(holidayCreate, dayJson, randomServerPort, calendarId);
	}

	private String post(String path, JSONObject jsonObj, Object... params) throws JsonMappingException, JsonProcessingException {

		HttpEntity<String> request = new HttpEntity<String>(jsonObj.toString(), headers);

		String response = restTemplate.postForObject(baseUrl + path, request, String.class, params);

		return new ObjectMapper().readTree(response).path("calendarId").asText();
	}

}
