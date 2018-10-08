package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LocalDateTimeGeneratorTest {

	@Test
	public void testForRange() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		List<LocalDateTime> randomDateTimes = Random.localDateTime().forRange(min, max).generateList(20);
		for (LocalDateTime randomDateTime : randomDateTimes) {
			assertTrue(randomDateTime.isAfter(min));
			assertTrue(randomDateTime.isBefore(max));
		}
	}

}
