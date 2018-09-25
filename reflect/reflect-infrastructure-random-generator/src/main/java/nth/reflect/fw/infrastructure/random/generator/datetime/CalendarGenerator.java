package nth.reflect.fw.infrastructure.random.generator.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class CalendarGenerator extends RandomGenerator<Calendar> {

	private final LocalDateTimeGenerator localDateTimeGenerator;

	public CalendarGenerator() {
		this(LocalDateTime.now(),LocalDateTime.now().plusDays(1));
	}
	
	public CalendarGenerator(LocalDateTime min, LocalDateTime max) {
		if (min.isAfter(max)) {
			LocalDateTime temp = min;
			min=max;
			max=temp;
		}
		localDateTimeGenerator = new LocalDateTimeGenerator(min, max);
	}
	
	public CalendarGenerator forRange(LocalDateTime min, LocalDateTime max) {
		return new CalendarGenerator(min, max);
	}

	@Override
	public Calendar generate() {
		LocalDateTime randomLocalDateTime = localDateTimeGenerator.generate();
		ZoneId zoneId = getZoneId();
		Calendar randomCalendar = GregorianCalendar.from(randomLocalDateTime.atZone(zoneId));
		return randomCalendar;
	}

	public static ZoneId getZoneId() {
		ZoneId zoneId = ZoneId.of("UTC");
		return zoneId;
	}

}
