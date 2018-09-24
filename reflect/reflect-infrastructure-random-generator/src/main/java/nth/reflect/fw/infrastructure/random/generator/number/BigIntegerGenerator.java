package nth.reflect.fw.infrastructure.random.generator.number;

import java.math.BigDecimal;
import java.math.BigInteger;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class BigIntegerGenerator implements RandomGenerator<BigInteger> {

	private final BigInteger min;
	private final BigInteger max;

	public BigIntegerGenerator() {
		this(BigInteger.ZERO, BigInteger.valueOf(Long.MAX_VALUE));
	}

	public BigIntegerGenerator(BigInteger min, BigInteger max) {
		if (min.compareTo(max) > 0) {
			BigInteger temp = min;
			min = max;
			max = temp;
		}
		this.min = min;
		this.max = max;
	}

	public BigIntegerGenerator forMin(BigInteger min) {
		return new BigIntegerGenerator(min,max);
	}
	
	public BigIntegerGenerator forMax(BigInteger max) {
		return new BigIntegerGenerator(min,max);
	}
	
	public BigIntegerGenerator forRange(BigInteger min, BigInteger max) {
		return new BigIntegerGenerator(min,max);
	}
	
	@Override
	public BigInteger generate() {
		BigDecimal range = new BigDecimal(max.subtract(min));
		return min.add(new BigDecimal(Math.random()).multiply(range).toBigInteger());
	}

}
