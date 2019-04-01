package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class CalendarGeneratorTest {
	@Test
	public void testForRange() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		List<Calendar> randomCalanders = Random.calendar().forRange(min, max).generateList(10);
		for (Calendar randomCalendar : randomCalanders) {
			LocalDateTime randomLocalDateTime = LocalDateTime.ofInstant(randomCalendar.toInstant(),
					CalendarGenerator.getZoneId());
			assertThat(randomLocalDateTime).isAfter(min).isBefore(max);
		}
	}

}
