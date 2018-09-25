package nth.reflect.fw.infrastructure.random.generator.number;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class LongGenerator extends RandomGenerator<Long> {

	private final long min;
	private final long max;

	public LongGenerator() {
		this(0, Long.MAX_VALUE);
	}

	public LongGenerator(long min, long max) {
		if (min > max) {
			long temp = min;
			min = max;
			max = temp;
		}
		this.min = min;
		this.max = max;
	}

	public LongGenerator forMax(long max) {
		return new LongGenerator(0, max);
	}

	public LongGenerator forRange(long min, long max) {
		return new LongGenerator(min, max);
	}

	@Override
	public Long generate() {
		return ThreadLocalRandom.current().nextLong(min, max);
	}

}
