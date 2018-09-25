package nth.reflect.fw.infrastructure.random.generator.datetime;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class LocalDateGenerator extends RandomGenerator<LocalDate> {

	private final LocalDate min;
	private final LocalDate max;
	
	public LocalDateGenerator() {
		this(LocalDate.now(),LocalDate.now().plusDays(1));
	}
	
	
	public LocalDateGenerator(LocalDate minDate, LocalDate maxDate) {
		if (minDate.isAfter(maxDate)) {
			LocalDate temp = minDate;
			minDate=maxDate;
			maxDate=temp;
		}
		this.min = minDate;
		this.max = maxDate;
	}
	
	public LocalDateGenerator forRange(LocalDate minDate, LocalDate maxDate) {
		return new LocalDateGenerator(minDate, maxDate);
	}

	@Override
	public LocalDate generate() {
		long minDay = min.toEpochDay();
		long maxDay = max.toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		return randomDate;
	}

}
