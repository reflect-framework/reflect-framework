package nth.reflect.fw.infrastructure.randomfactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RandomCalendarFactory implements Factory<Calendar> {

	private final LocalDateTime min;
	private final LocalDateTime max;

	public RandomCalendarFactory(LocalDateTime min, LocalDateTime max) {
		super();
		this.min = min;
		this.max = max;
	}

	@Override
	public Calendar create() {
		LocalDateTime randomLocalDateTime = RandomLocalDateTimeFactory.create(min, max);
		ZoneId zoneId = getZoneId();
		Calendar randomCalendar = GregorianCalendar.from(randomLocalDateTime.atZone(zoneId));
		return randomCalendar;
	}

	public static ZoneId getZoneId() {
		ZoneId zoneId = ZoneId.of("UTC");
		return zoneId;
	}

}
