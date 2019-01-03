package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class FloatGeneratorTest {

	@Test
	public void testForNoParameter() {
		float min = Float.MIN_VALUE;
		float max = Float.MAX_VALUE;
		List<Float> randomFloats = Random.float_().generateList(20);
		assertThat(randomFloats, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		float min = Float.MIN_VALUE;
		float max = Float.MAX_VALUE;
		List<Float> randomFloats = Random.float_().forMax(max).generateList(20);
		assertThat(randomFloats, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

		max = 10;
		randomFloats = Random.float_().forMax(max).generateList(20);
		assertThat(randomFloats, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForRange() {
		float min = 0;
		float max = 1;
		List<Float> randomFloats = Random.float_().forRange(min, max).generateList(20);
		assertThat(randomFloats, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

		min = 5;
		max = 10;
		randomFloats = Random.float_().forRange(min, max).generateList(20);
		assertThat(randomFloats, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

		min = 10;
		max = 5;
		randomFloats = Random.float_().forRange(min, max).generateList(20);
		assertThat(randomFloats, everyItem(allOf(greaterThanOrEqualTo(max), lessThanOrEqualTo(min))));
	}

}
