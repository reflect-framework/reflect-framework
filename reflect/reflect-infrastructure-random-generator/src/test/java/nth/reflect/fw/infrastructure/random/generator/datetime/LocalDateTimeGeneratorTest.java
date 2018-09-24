package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.datetime.LocalDateTimeGenerator;

public class LocalDateTimeGeneratorTest {

	@Test
	public void testRandomLocalDateTimeFactoryTest() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		LocalDateTimeGenerator localDateTimeGenerator = new LocalDateTimeGenerator(min, max);
		for (int j = 0; j < 10; j++) {
			LocalDateTime randomDateTime = localDateTimeGenerator.generate();
			assertTrue(randomDateTime.compareTo(min) >= 0);
			assertTrue(randomDateTime.compareTo(max) <= 0);
		}
	}

}
