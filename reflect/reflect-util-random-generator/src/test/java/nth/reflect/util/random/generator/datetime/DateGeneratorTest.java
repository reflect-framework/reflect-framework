package nth.reflect.util.random.generator.datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.datetime.CalendarGenerator;

public class DateGeneratorTest {
	@Test
	public void testForRange() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		List<Date> randomDates = Random.date().forRange(min, max).generateList(10);
		for (Date randomDate : randomDates) {
			LocalDateTime randomLocalDateTime = LocalDateTime.ofInstant(randomDate.toInstant(),
					CalendarGenerator.getZoneId());
			assertThat(randomLocalDateTime).isAfter(min).isBefore(max);
		}
	}

}
