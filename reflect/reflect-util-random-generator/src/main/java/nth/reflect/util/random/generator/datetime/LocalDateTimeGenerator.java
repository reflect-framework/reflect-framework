package nth.reflect.util.random.generator.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import nth.reflect.util.random.RandomGenerator;

public class LocalDateTimeGenerator extends RandomGenerator<LocalDateTime> {

	private final LocalTimeGenerator localTimeGenerator;
	private final LocalDateGenerator localDateGenerator;

	public LocalDateTimeGenerator() {
		this(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
	}

	public LocalDateTimeGenerator(LocalDateTime min, LocalDateTime max) {
		if (min.isAfter(max)) {
			LocalDateTime temp = min;
			min = max;
			max = temp;
		}
		int periodInDays = Period.between(min.toLocalDate(), max.toLocalDate()).getDays();
		if (periodInDays >= 1) {
			localTimeGenerator = new LocalTimeGenerator(LocalTime.MIN, LocalTime.MAX);
		} else {
			localTimeGenerator = new LocalTimeGenerator(min.toLocalTime(), max.toLocalTime());
		}
		localDateGenerator = new LocalDateGenerator(min.toLocalDate(), max.toLocalDate());
	}

	public LocalDateTimeGenerator forRange(LocalDateTime min, LocalDateTime max) {
		return new LocalDateTimeGenerator(min, max);
	}

	@Override
	public LocalDateTime generate() {
		LocalTime randomTime = localTimeGenerator.generate();
		LocalDate randomDate = localDateGenerator.generate();
		return LocalDateTime.of(randomDate, randomTime);
	}

}
