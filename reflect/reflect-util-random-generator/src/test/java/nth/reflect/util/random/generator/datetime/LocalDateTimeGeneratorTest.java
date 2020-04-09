package nth.reflect.util.random.generator.datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class LocalDateTimeGeneratorTest {
	@Test
	public void testForRange() {
		LocalDateTime min = LocalDateTime.of(1970, 1, 1, 9, 10, 12);
		LocalDateTime max = LocalDateTime.of(2015, 12, 31, 15, 45, 22, 99);
		List<LocalDateTime> randomDateTimes = Random.localDateTime().forRange(min, max).generateList(20);
		assertThat(randomDateTimes).allSatisfy(d -> assertThat(d).isAfter(min).isBefore(max));
	}

}
