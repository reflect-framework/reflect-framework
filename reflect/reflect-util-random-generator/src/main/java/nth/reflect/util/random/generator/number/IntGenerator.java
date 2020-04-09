package nth.reflect.util.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;

public class IntGenerator extends RandomGenerator<Integer> {

	private final int min;
	private final int max;

	public IntGenerator() {
		this(0, Integer.MAX_VALUE);
	}

	public IntGenerator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public IntGenerator forMax(int max) {
		return new IntGenerator(0, max);
	}

	public IntGenerator forRange(int min, int max) {
		return new IntGenerator(min, max);
	}

	@Override
	public Integer generate() {
		if (min >= max) {
			return min;
		}
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
