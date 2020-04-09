package nth.reflect.util.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;

public class DoubleGenerator extends RandomGenerator<Double> {

	private final double min;
	private final double max;

	public DoubleGenerator() {
		this(0, Double.MAX_VALUE);
	}

	public DoubleGenerator(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public DoubleGenerator forMax(double max) {
		return new DoubleGenerator(0, max);
	}

	public DoubleGenerator forRange(double min, double max) {
		return new DoubleGenerator(min, max);
	}

	@Override
	public Double generate() {
		if (min >= max) {
			return min;
		}
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

}
