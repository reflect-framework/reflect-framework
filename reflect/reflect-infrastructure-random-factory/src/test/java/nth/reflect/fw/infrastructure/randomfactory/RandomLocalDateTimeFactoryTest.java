package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class RandomLocalDateTimeFactoryTest {

	@Test
	public void testRandomLocalDateTimeFactoryTest() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		RandomLocalDateTimeFactory randomLocalDateTimeFactory = new RandomLocalDateTimeFactory(min, max);
		for (int j = 0; j < 10; j++) {
			LocalDateTime randomDateTime = randomLocalDateTimeFactory.create();
			assertTrue(randomDateTime.compareTo(min) >= 0);
			assertTrue(randomDateTime.compareTo(max) <= 0);
		}
	}

}
