package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class BigIntegerGeneratorTest {

	@Test
	public void testForNoParam() {
		List<BigInteger> randomBigIntegers = Random.bigInteger().generateList(10);
		BigInteger min = BigInteger.ZERO;
		BigInteger max = BigInteger.valueOf(Long.MAX_VALUE);
		assertThat(randomBigIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		testForMax(10);
		testForMax(-10);
		testForMax(-20);
		testForMax(10);
	}

	private void testForMax(int maxInt) {
		BigInteger min = BigInteger.ZERO;
		BigInteger max = BigInteger.valueOf(maxInt);
		List<BigInteger> randomBigIntegers = Random.bigInteger().forMax(max).generateList(20);
		if (min.compareTo(max) == 1) {
			BigInteger temp = min;
			min = max;
			max = temp;
		}
		assertThat(randomBigIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForRange() {
		testForRange(10, 20);
		testForRange(-10, 10);
		testForRange(-20, -10);
		testForRange(10, 10);
	}

	private void testForRange(int minInt, int maxInt) {
		BigInteger min = BigInteger.valueOf(minInt);
		BigInteger max = BigInteger.valueOf(maxInt);
		List<BigInteger> randomBigIntegers = Random.bigInteger().forRange(min, max).generateList(20);
		assertThat(randomBigIntegers, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

}
