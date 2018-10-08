package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class IntGeneratorTest {

	@Test
	public void testForNoParameter() {
		int min=0;
		int max=Integer.MAX_VALUE;
		List<Integer> randomIntegers = Random.integer().generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		int min=0;
		int max=1;
		List<Integer> randomIntegers = Random.integer().forMax( max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		max=10;
		randomIntegers = Random.integer().forMax( max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	
	@Test
	public void testForRange() {
		int min=0;
		int max=1;
		List<Integer> randomIntegers = Random.integer().forRange(min, max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		min=5;
		max=10;
		randomIntegers = Random.integer().forRange(min, max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
		
		min=10;
		max=5;
		randomIntegers = Random.integer().forRange(min, max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(max), lessThanOrEqualTo(min))));
	}


}
