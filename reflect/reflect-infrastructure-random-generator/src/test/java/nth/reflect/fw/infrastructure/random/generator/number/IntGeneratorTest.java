package nth.reflect.fw.infrastructure.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class IntGeneratorTest {

	@Test
	public void testForNoParameter() {
		int min = 0;
		int max = Integer.MAX_VALUE;
		List<Integer> randomIntegers = Random.integer().generateList(20);
		assertThat(randomIntegers).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
	}

	@Test
	public void testForMax() {
		List<Integer> randomIntegers = Random.integer().forMax(1).generateList(20);
		assertThat(randomIntegers).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1));

		randomIntegers = Random.integer().forMax(10).generateList(20);
		assertThat(randomIntegers).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10));
	}

	@Test
	public void testForRange() {
		List<Integer> randomIntegers = Random.integer().forRange(0, 1).generateList(20);
		assertThat(randomIntegers).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1));

		randomIntegers = Random.integer().forRange(5, 10).generateList(20);
		assertThat(randomIntegers).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));

		randomIntegers = Random.integer().forRange(10, 5).generateList(20);
		assertThat(randomIntegers).allSatisfy(i -> assertThat(i).isGreaterThanOrEqualTo(10).isLessThanOrEqualTo(10));
	}

}
