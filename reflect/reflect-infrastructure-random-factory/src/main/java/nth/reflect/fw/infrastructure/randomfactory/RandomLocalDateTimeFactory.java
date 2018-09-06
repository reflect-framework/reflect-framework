package nth.reflect.fw.infrastructure.randomfactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RandomLocalDateTimeFactory implements Factory<LocalDateTime> {

	private final LocalDateTime min;
	private final LocalDateTime max;
	
	public RandomLocalDateTimeFactory(LocalDateTime min, LocalDateTime max) {
		super();
		this.min = min;
		this.max = max;
	}

	@Override
	public LocalDateTime create() {
		return create(min,max);
	}

	public static LocalDateTime create(LocalDateTime min, LocalDateTime max) {
		LocalTime randomTime = RandomLocalTimeFactory.create(min.toLocalTime(), max.toLocalTime());
		LocalDate randomDate=RandomLocalDateFactory.create(min.toLocalDate(), max.toLocalDate());
		return LocalDateTime.of(randomDate, randomTime);
	}

}
