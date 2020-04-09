package nth.reflect.util.random.generator.datetime;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;

public class LocalTimeGenerator extends RandomGenerator<LocalTime> {

	private final LocalTime min;
	private final LocalTime max;

	public LocalTimeGenerator() {
		this(LocalTime.MIN, LocalTime.MAX);
	}

	public LocalTimeGenerator(LocalTime min, LocalTime max) {
		if (min.isAfter(max)) {
			LocalTime temp = min;
			min = max;
			max = temp;
		}
		this.min = min;
		this.max = max;
	}

	public LocalTimeGenerator forRange(LocalTime min, LocalTime max) {
		return new LocalTimeGenerator(min, max);
	}

	@Override
	public LocalTime generate() {
		long minNano = min.toNanoOfDay();
		long maxNamo = max.toNanoOfDay();
		long randomNano = ThreadLocalRandom.current().nextLong(minNano, maxNamo);
		LocalTime randomTime = LocalTime.ofNanoOfDay(randomNano);
		return randomTime;
	}

}
