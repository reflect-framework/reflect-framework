package nth.reflect.util.random.generator.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.util.random.RandomGenerator;

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
	
	/**
	 * e.g. for generating birthday dates for people between a given age.
	 * @param minPeriodBack from to now
	 * @param maxPeriodBack from to now
	 * @return {@link RandomGenerator} that generates a random date in the past
	 */
	public LocalDateGenerator forPeriodInThePast(Period minPeriodBack, Period maxPeriodBack) {
		if (minPeriodBack.getDays()>maxPeriodBack.getDays()) {
			Period temp = minPeriodBack;
			minPeriodBack=maxPeriodBack;
			maxPeriodBack=temp;
		}
		LocalDate now = LocalDate.now();
		LocalDate endDate=now.minus(minPeriodBack);
		LocalDate startDate=now.minus(maxPeriodBack);
		return new LocalDateGenerator(startDate, endDate);
	}
	
	/**
	 * e.g. for generating due dates
	 * @param minPeriodForward from to now
	 * @param maxPeriodForward from to now
	 * @return {@link RandomGenerator} that generates a random date in the future
	 */
	public LocalDateGenerator forPeriodInTheFuture(Period minPeriodForward, Period maxPeriodForward) {
		if (minPeriodForward.getDays() > maxPeriodForward.getDays()) {
			Period temp = maxPeriodForward;
			minPeriodForward=maxPeriodForward;
			maxPeriodForward=temp;
		}
		LocalDate now = LocalDate.now();
		LocalDate startDate=now.plus(minPeriodForward);
		LocalDate endDate=now.plus(maxPeriodForward);
		return new LocalDateGenerator(startDate, endDate);
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
