package nth.reflect.fw.infrastructure.random.generator.number;

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(randomBigIntegers)
				.allSatisfy(bigInt -> assertThat(bigInt).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
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
		if (min.compareTo(max) < 0) {
			assertThat(randomBigIntegers)
					.allSatisfy(bigInt -> assertThat(bigInt).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
		} else {
			assertThat(randomBigIntegers)
					.allSatisfy(bigInt -> assertThat(bigInt).isGreaterThanOrEqualTo(max).isLessThanOrEqualTo(min));
		}
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
		assertThat(randomBigIntegers)
				.allSatisfy(bigInt -> assertThat(bigInt).isGreaterThanOrEqualTo(min).isLessThanOrEqualTo(max));
	}

}
