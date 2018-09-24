package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class DoubleGenerator implements RandomGenerator<Double> {

	private final double min;
	private final double max;

	
	public DoubleGenerator() {
		this(0,Double.MAX_VALUE);
	}
	
	public DoubleGenerator(double min, double max) {
		if (min>max) {
			double temp = min;
			min=max;
			max=temp;
		}
		this.min = min;
		this.max = max;
	}

	public DoubleGenerator forMin(double min) {
		return new DoubleGenerator(min,max);
	}
	
	public DoubleGenerator forMax(double max) {
		return new DoubleGenerator(min,max);
	}
	
	public DoubleGenerator forRange(double min, double max) {
		return new DoubleGenerator(min,max);
	}
	
	@Override
	public Double generate() {
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

}
