package nth.reflect.util.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class DoubleGeneratorTest {

	@Test
	public void testForNoParameter() {
		double min = 0;
		double max = Double.MAX_VALUE;
		List<Double> randomDoubles = Random.double_().generateList(20);
		assertThat(randomDoubles).allSatisfy(d -> assertThat(d).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
	}

	@Test
	public void testForMax() {
		List<Double> randomDoubles = Random.double_().forMax(1).generateList(20);
		assertThat(randomDoubles).allSatisfy(d -> assertThat(d).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(20));

		randomDoubles = Random.double_().forMax(10).generateList(20);
		assertThat(randomDoubles).allSatisfy(d -> assertThat(d).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10));
	}

	@Test
	public void testForRange() {
		List<Double> randomDoubles = Random.double_().forRange(0, 1).generateList(20);
		assertThat(randomDoubles).allSatisfy(d -> assertThat(d).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1));

		randomDoubles = Random.double_().forRange(5, 10).generateList(20);
		assertThat(randomDoubles).allSatisfy(d -> assertThat(d).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));

		randomDoubles = Random.double_().forRange(10, 5).generateList(20);
		assertThat(randomDoubles).allSatisfy(d -> assertThat(d).isGreaterThanOrEqualTo(10).isLessThanOrEqualTo(10));
	}
}
