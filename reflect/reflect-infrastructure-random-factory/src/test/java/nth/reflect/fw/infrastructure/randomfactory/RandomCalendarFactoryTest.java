package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.junit.Test;

public class RandomCalendarFactoryTest {

	@Test
	public void testRandomCalendarFactoryTest() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		RandomCalendarFactory randomCalenderFactory = new RandomCalendarFactory(min, max);
		for (int j = 0; j < 10; j++) {
			Calendar randomCalendar = randomCalenderFactory.create();
			LocalDateTime randomLocalDateTime = LocalDateTime.ofInstant(randomCalendar.toInstant(),
			            RandomCalendarFactory.getZoneId());
			assertTrue(randomLocalDateTime.compareTo(min) >= 0);
			assertTrue(randomLocalDateTime.compareTo(max) <= 0);
		}
	}

}
