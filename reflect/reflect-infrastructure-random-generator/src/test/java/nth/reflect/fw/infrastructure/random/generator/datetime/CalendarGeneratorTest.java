package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.datetime.CalendarGenerator;

public class CalendarGeneratorTest {

	@Test
	public void testForRange() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		List<Calendar> randomCalanders = Random.calendarGenerator().forRange(min, max).generateList(10);
		for (Calendar randomCalendar : randomCalanders) {
			LocalDateTime randomLocalDateTime = LocalDateTime.ofInstant(randomCalendar.toInstant(),
			            CalendarGenerator.getZoneId());
			assertTrue(randomLocalDateTime.isAfter(min));
			assertTrue(randomLocalDateTime.isBefore(max));
		}
	}

}
