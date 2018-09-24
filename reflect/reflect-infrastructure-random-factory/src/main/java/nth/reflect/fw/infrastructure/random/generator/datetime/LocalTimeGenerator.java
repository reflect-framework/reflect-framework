package nth.reflect.fw.infrastructure.randomfactory;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLocalTimeFactory implements Factory<LocalTime> {

	private final LocalTime min;
	private final LocalTime max;
	
	public RandomLocalTimeFactory(LocalTime min, LocalTime max) {
		super();
		this.min = min;
		this.max = max;
	}

	@Override
	public LocalTime create() {
		return create(min,max);
	}

	public static LocalTime create(LocalTime min, LocalTime max) {
		long minNano = min.toNanoOfDay();
		long maxNamo = max.toNanoOfDay();
		long randomNano = ThreadLocalRandom.current().nextLong(minNano, maxNamo);
		LocalTime randomTime = LocalTime.ofNanoOfDay(randomNano);
		return randomTime;
	}

}
