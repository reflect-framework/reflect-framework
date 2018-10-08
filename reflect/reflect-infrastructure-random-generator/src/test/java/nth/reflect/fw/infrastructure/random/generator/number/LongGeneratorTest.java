package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LongGeneratorTest {

	@Test
	public void testForNoParameter() {
		long min=0;
		long max=Long.MAX_VALUE;
		List<Long> randomLongs = Random.long_().generateList(20);
		assertThat(randomLongs, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		long min=0;
		long max=1;
		List<Long> randomLongs = Random.long_().forMax( max).generateList(20);
		assertThat(randomLongs, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		max=10;
		randomLongs = Random.long_().forMax( max).generateList(20);
		assertThat(randomLongs, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	
	@Test
	public void testForRange() {
		long min=0;
		long max=1;
		List<Long> randomLongs = Random.long_().forRange(min, max).generateList(20);
		assertThat(randomLongs, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		min=5;
		max=10;
		randomLongs = Random.long_().forRange(min, max).generateList(20);
		assertThat(randomLongs, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		min=10;
		max=5;
		randomLongs = Random.long_().forRange(min, max).generateList(20);
		assertThat(randomLongs, everyItem(allOf(greaterThanOrEqualTo(max), lessThanOrEqualTo(min))));
	}


}
