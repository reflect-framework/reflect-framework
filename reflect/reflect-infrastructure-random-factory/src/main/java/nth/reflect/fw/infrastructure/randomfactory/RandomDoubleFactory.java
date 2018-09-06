package nth.reflect.fw.infrastructure.randomfactory;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDoubleFactory implements Factory<Double> {

	private final double min;
	private final double max;

	
	public RandomDoubleFactory(double min, double max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public Double create() {
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

}
