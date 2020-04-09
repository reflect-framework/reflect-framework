package nth.reflect.util.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class LongGeneratorTest {

	@Test
	public void testForNoParameter() {
		long min = 0;
		long max = Long.MAX_VALUE;
		List<Long> randomLongs = Random.long_().generateList(20);
		assertThat(randomLongs).allSatisfy(l -> assertThat(l).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
	}

	@Test
	public void testForMax() {
		List<Long> randomLongs = Random.long_().forMax(1).generateList(20);
		assertThat(randomLongs).allSatisfy(l -> assertThat(l).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1));

		randomLongs = Random.long_().forMax(10).generateList(20);
		assertThat(randomLongs).allSatisfy(l -> assertThat(l).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10));
	}

	@Test
	public void testForRange() {
		List<Long> randomLongs = Random.long_().forRange(0, 1).generateList(20);
		assertThat(randomLongs).allSatisfy(l -> assertThat(l).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1));

		randomLongs = Random.long_().forRange(5, 10).generateList(20);
		assertThat(randomLongs).allSatisfy(l -> assertThat(l).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));

		randomLongs = Random.long_().forRange(10, 5).generateList(20);
		assertThat(randomLongs).allSatisfy(l -> assertThat(l).isGreaterThanOrEqualTo(10).isLessThanOrEqualTo(10));
	}

}
