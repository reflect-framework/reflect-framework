package nth.reflect.fw.infrastructure.randomfactory;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLocalDateFactory implements Factory<LocalDate> {

	private final LocalDate min;
	private final LocalDate max;
	
	public RandomLocalDateFactory(LocalDate minDate, LocalDate maxDate) {
		super();
		this.min = minDate;
		this.max = maxDate;
	}

	@Override
	public LocalDate create() {
		return create(min,max);
	}

	public static LocalDate create(LocalDate min, LocalDate max) {
		long minDay = min.toEpochDay();
		long maxDay = max.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		return randomDate;
	}

}
