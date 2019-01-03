package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class ByteGeneratorTest {

	@Test
	public void testForNoParameter() {
		byte min = Byte.MIN_VALUE;
		byte max = Byte.MAX_VALUE;
		List<Byte> randomBytes = Random.byte_().generateList(20);
		assertThat(randomBytes, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		byte min = Byte.MIN_VALUE;
		byte max = 1;
		List<Byte> randomBytes = Random.byte_().forMax(max).generateList(20);
		assertThat(randomBytes, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

		max = 10;
		randomBytes = Random.byte_().forMax(max).generateList(20);
		assertThat(randomBytes, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForRange() {
		byte min = 0;
		byte max = 1;
		List<Byte> randomIntegers = Random.byte_().forRange(min, max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

		min = 5;
		max = 10;
		randomIntegers = Random.byte_().forRange(min, max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));

		min = 10;
		max = 5;
		randomIntegers = Random.byte_().forRange(min, max).generateList(20);
		assertThat(randomIntegers, everyItem(allOf(greaterThanOrEqualTo(max), lessThanOrEqualTo(min))));
	}

}
