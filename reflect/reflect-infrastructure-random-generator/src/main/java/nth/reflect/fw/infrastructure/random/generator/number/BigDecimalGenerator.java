package nth.reflect.fw.infrastructure.random.generator.number;

import java.math.BigDecimal;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class BigDecimalGenerator extends RandomGenerator<BigDecimal> {

	private final BigDecimal min;
	private final BigDecimal max;
	
	public BigDecimalGenerator() {
		this(BigDecimal.ZERO,BigDecimal.valueOf(Double.MAX_VALUE));
	}
	
	public BigDecimalGenerator(BigDecimal min, BigDecimal max) {
		if (min.compareTo(max)>0) {
			BigDecimal temp = min;
			min=max;
			max=temp;
		}
		this.min = min;
		this.max = max;
	}

	public BigDecimalGenerator forMax(BigDecimal max) {
		return new BigDecimalGenerator(BigDecimal.ZERO, max);
	}

	
	public BigDecimalGenerator forRange(BigDecimal min, BigDecimal max) {
		return new BigDecimalGenerator(min, max);
	}

	@Override
	public BigDecimal generate() {
		return  min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	}


}
