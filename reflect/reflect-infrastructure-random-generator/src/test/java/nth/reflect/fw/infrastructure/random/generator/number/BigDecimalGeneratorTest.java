package nth.reflect.fw.infrastructure.random.generator.number;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class BigDecimalGeneratorTest {
	@Test
	public void testForNoParams() {
		List<BigDecimal> randomBigDecimals = Random.bigDecimalGenerator().generateList(20);
		BigDecimal min = BigDecimal.ZERO;
		BigDecimal max = BigDecimal.valueOf(Double.MAX_VALUE);
		assertThat(randomBigDecimals, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForMax() {
		testForMax(BigDecimal.valueOf(10));
		testForMax(BigDecimal.valueOf(-10));
		testForMax(BigDecimal.valueOf(-20));
		testForMax(BigDecimal.valueOf(10));

		testForMax(new BigDecimal("10.5"));
		testForMax(new BigDecimal("-10.4"));
		testForMax(new BigDecimal("-20.3"));
		testForMax(new BigDecimal("10.9"));
	}

	private void testForMax(BigDecimal max) {
		List<BigDecimal> randomBigDecimals = Random.bigDecimalGenerator().forMax(max).generateList(20);
		BigDecimal min = BigDecimal.ZERO;
		if (min.compareTo(max) > 0) {
			BigDecimal temp = min;
			min = max;
			max = temp;
		}
		assertThat(randomBigDecimals, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

	@Test
	public void testForRange() {
		testForRange(BigDecimal.valueOf(10), BigDecimal.valueOf(20));
		testForRange(BigDecimal.valueOf(-10), BigDecimal.valueOf(10));
		testForRange(BigDecimal.valueOf(-20), BigDecimal.valueOf(-10));
		testForRange(BigDecimal.valueOf(10), BigDecimal.valueOf(10));

		testForRange(new BigDecimal("10.5"), new BigDecimal("20.3"));
		testForRange(new BigDecimal("-10.4"), new BigDecimal("10.9"));
		testForRange(new BigDecimal("-20.3"), new BigDecimal("-10.4"));
		testForRange(new BigDecimal("10.9"), new BigDecimal("10.9"));
	}

	private void testForRange(BigDecimal min, BigDecimal max) {
		List<BigDecimal> randomBigDecimals = Random.bigDecimalGenerator().forRange(min, max).generateList(20);
		assertThat(randomBigDecimals, everyItem(allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))));
	}

}
