package es.ozona.kayros.webapp.shareddomain.model;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomZonedDateTimeDeserializerTest {

	private CustomZonedDateTimeDeserializer deserilizer;
	private ZonedDateTime date;

	@BeforeEach
	public void init() {

		deserilizer = new CustomZonedDateTimeDeserializer();
		date = ZonedDateTime.now();

	}

	@Test
	public void test1() {

	}

	@Test
	public void test2() {

	}

	@Test
	public void test3() {

	}

}
