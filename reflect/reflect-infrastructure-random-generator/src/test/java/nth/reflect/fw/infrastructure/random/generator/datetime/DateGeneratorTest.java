package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class DateGeneratorTest {

	@Test
	public void testForRange() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		List<Date> randomDates = Random.date().forRange(min, max).generateList(10);
		for (Date randomDate : randomDates) {
			LocalDateTime randomLocalDateTime = LocalDateTime.ofInstant(randomDate.toInstant(),
					CalendarGenerator.getZoneId());
			assertTrue(randomLocalDateTime.isAfter(min));
			assertTrue(randomLocalDateTime.isBefore(max));
		}
	}

}
