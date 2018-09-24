package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.datetime.CalendarGenerator;

public class CalendarGeneratorTest {

	@Test
	public void testRandomCalendarFactoryTest() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		CalendarGenerator calenderGenerator = new CalendarGenerator(min, max);
		for (int j = 0; j < 10; j++) {
			Calendar randomCalendar = calenderGenerator.generate();
			LocalDateTime randomLocalDateTime = LocalDateTime.ofInstant(randomCalendar.toInstant(),
			            CalendarGenerator.getZoneId());
			assertTrue(randomLocalDateTime.compareTo(min) >= 0);
			assertTrue(randomLocalDateTime.compareTo(max) <= 0);
		}
	}

}
