package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LocalTimeGeneratorTest {
	@Test
	public void testForRange() {
		LocalTime min = LocalTime.of(9, 10, 12);
		LocalTime max = LocalTime.of(15, 45, 22, 99);
		List<LocalTime> randomTimes = Random.localTime().forRange(min, max).generateList(20);
		assertThat(randomTimes).allSatisfy(d -> assertThat(d).isAfter(min).isBefore(max));
	}

}
