package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class DoubleGeneratorTest {

	@Test
	public void testForNoParameter() {
		double min=0;
		double max=Double.MAX_VALUE;
		List<Double> randomDoubles = Random.double_().generateList(20);
		assertThat(randomDoubles, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		double min=0;
		double max=1;
		List<Double> randomDoubles = Random.double_().forMax( max).generateList(20);
		assertThat(randomDoubles, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		max=10;
		randomDoubles = Random.double_().forMax( max).generateList(20);
		assertThat(randomDoubles, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	
	@Test
	public void testForRange() {
		double min=0;
		double max=1;
		List<Double> randomDoubles = Random.double_().forRange(min, max).generateList(20);
		assertThat(randomDoubles, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		min=5;
		max=10;
		randomDoubles = Random.double_().forRange(min, max).generateList(20);
		assertThat(randomDoubles, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		min=10;
		max=5;
		randomDoubles = Random.double_().forRange(min, max).generateList(20);
		assertThat(randomDoubles, everyItem(allOf(greaterThanOrEqualTo(max), lessThanOrEqualTo(min))));
	}
}
