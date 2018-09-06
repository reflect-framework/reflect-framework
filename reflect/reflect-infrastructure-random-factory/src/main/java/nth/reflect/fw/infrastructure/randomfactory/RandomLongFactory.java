package nth.reflect.fw.infrastructure.randomfactory;

import java.util.concurrent.ThreadLocalRandom;

public class RandomLongFactory implements Factory<Long> {

	private final long min;
	private final long max;

	
	public RandomLongFactory(long min, long max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public Long create() {
		return ThreadLocalRandom.current().nextLong(min, max);
	}

}
