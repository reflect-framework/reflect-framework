package nth.reflect.fw.infrastructure.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class FloatGeneratorTest {

	@Test
	public void testForNoParameter() {
		float min = Float.MIN_VALUE;
		float max = Float.MAX_VALUE;
		List<Float> randomFloats = Random.float_().generateList(20);
		assertThat(randomFloats).allSatisfy(f -> assertThat(f).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
	}

	@Test
	public void testForMax() {
		float min = Float.MIN_VALUE;
		float max = Float.MAX_VALUE;
		List<Float> randomFloats = Random.float_().forMax(max).generateList(20);
		assertThat(randomFloats).allSatisfy(f -> assertThat(f).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));

		randomFloats = Random.float_().forMax(10).generateList(20);
		assertThat(randomFloats).allSatisfy(f -> assertThat(f).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10));
	}

	@Test
	public void testForRange() {
		List<Float> randomFloats = Random.float_().forRange(0, 1).generateList(20);
		assertThat(randomFloats).allSatisfy(f -> assertThat(f).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1));

		randomFloats = Random.float_().forRange(5, 10).generateList(20);
		assertThat(randomFloats).allSatisfy(f -> assertThat(f).isGreaterThanOrEqualTo(5).isLessThanOrEqualTo(10));

		randomFloats = Random.float_().forRange(10, 5).generateList(20);
		assertThat(randomFloats).allSatisfy(f -> assertThat(f).isGreaterThanOrEqualTo(10).isLessThanOrEqualTo(10));
	}

}
