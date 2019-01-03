package nth.reflect.fw.infrastructure.random.generator.datetime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class DateGenerator extends RandomGenerator<Date> {

	private final LocalDateTimeGenerator localDateTimeGenerator;

	public DateGenerator() {
		this(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
	}

	public DateGenerator(LocalDateTime min, LocalDateTime max) {
		if (min.isAfter(max)) {
			LocalDateTime temp = min;
			min = max;
			max = temp;
		}
		localDateTimeGenerator = new LocalDateTimeGenerator(min, max);
	}

	public DateGenerator forRange(LocalDateTime min, LocalDateTime max) {
		return new DateGenerator(min, max);
	}

	@Override
	public Date generate() {
		LocalDateTime randomLocalDateTime = localDateTimeGenerator.generate();
		Date date = Date.from(randomLocalDateTime.toInstant(ZoneOffset.UTC));
		return date;
	}

}
