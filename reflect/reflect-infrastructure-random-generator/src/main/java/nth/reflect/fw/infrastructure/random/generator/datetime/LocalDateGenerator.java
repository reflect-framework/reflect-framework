package nth.reflect.fw.infrastructure.random.generator.datetime;

import java.time.LocalDate;
import java.time.Period;
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
	
	/**
	 * e.g. for generating birthday dates for people between a given age.
	 * @param minYearsBack
	 * @param maxYearsBack
	 * @return {@link RandomGenerator} that generates a random date in the past
	 */
	public LocalDateGenerator forYearsBack(int minYearsBack, int maxYearsBack) {
		if (minYearsBack>maxYearsBack) {
			int temp = minYearsBack;
			minYearsBack=maxYearsBack;
			maxYearsBack=temp;
		}
		LocalDate now = LocalDate.now();
		LocalDate endDate=now.minus(Period.ofYears(minYearsBack));
		LocalDate startDate=now.minus(Period.ofYears(maxYearsBack));
		return new LocalDateGenerator(startDate, endDate);
	}
	
	/**
	 * e.g. for generating due dates
	 * @param minYearsForward
	 * @param maxYearsForward
	 * @return {@link RandomGenerator} that generates a random date in the future
	 */
	public LocalDateGenerator forYearsForward(int minYearsForward, int maxYearsForward) {
		if (minYearsForward>maxYearsForward) {
			int temp = minYearsForward;
			minYearsForward=maxYearsForward;
			maxYearsForward=temp;
		}
		LocalDate now = LocalDate.now();
		LocalDate startDate=now.plus(Period.ofYears(minYearsForward));
		LocalDate endDate=now.plus(Period.ofYears(maxYearsForward));
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
