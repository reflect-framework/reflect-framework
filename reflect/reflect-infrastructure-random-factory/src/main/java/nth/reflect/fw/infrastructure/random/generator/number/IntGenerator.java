package nth.reflect.fw.infrastructure.randomfactory;

import java.util.concurrent.ThreadLocalRandom;

public class RandomIntFactory implements Factory<Integer> {

	private final int min;
	private final int max;

	
	public RandomIntFactory(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public Integer create() {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
