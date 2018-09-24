package nth.reflect.fw.infrastructure.random.generator.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class LocalDateTimeGenerator implements RandomGenerator<LocalDateTime> {

	private final LocalDateTime min;
	private final LocalDateTime max;
	
	public LocalDateTimeGenerator() {
		this(LocalDateTime.now(),LocalDateTime.now().plusDays(1));
	}
	
	public LocalDateTimeGenerator(LocalDateTime min, LocalDateTime max) {
		if (min.isAfter(max)) {
			LocalDateTime temp = min;
			min=max;
			max=temp;
		}
		this.min = min;
		this.max = max;
	}

	public LocalDateTimeGenerator forRange(LocalDateTime min, LocalDateTime max) {
		return new LocalDateTimeGenerator(min, max);
	}
	
	@Override
	public LocalDateTime generate() {
		LocalTime randomTime = new LocalTimeGenerator(min.toLocalTime(), max.toLocalTime()).generate();
		LocalDate randomDate=new LocalDateGenerator(min.toLocalDate(), max.toLocalDate()).generate();
		return LocalDateTime.of(randomDate, randomTime);
	}

}
