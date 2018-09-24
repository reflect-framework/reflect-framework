package nth.reflect.fw.infrastructure.randomfactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBigIntegerFactory implements Factory<BigInteger> {

	private final Factory<BigInteger> randomBigIntegerFactory;

	/**
	 * 
	 * @param bitLength
	 *            =size of {@link BigInteger}. e.g.: bitLength=4: max
	 *            value=2^4-1=15
	 */
	public RandomBigIntegerFactory(int bitLength) {
		randomBigIntegerFactory = new Factory<BigInteger>() {

			@Override
			public BigInteger create() {
				return createRandomBigInteger(bitLength);
			}
		};
	}

	public RandomBigIntegerFactory(BigInteger min, BigInteger max) {
		BigDecimal range = new BigDecimal(max.subtract(min));
		randomBigIntegerFactory = new Factory<BigInteger>() {

			@Override
			public BigInteger create() {
				return min.add(new BigDecimal(Math.random()).multiply(range).toBigInteger());
			}
		};
	}

	public BigInteger createRandomBigInteger(int bitLength) {
		BigInteger result;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		result = new BigInteger(bitLength, random);
		// rounding r.setScale(nrOfDecimals, ROUNDING_MODE);
		return result;
	}

	@Override
	public BigInteger create() {
		return randomBigIntegerFactory.create();
	}

}
