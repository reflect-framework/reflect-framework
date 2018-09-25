package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LocalDateGeneratorTest {

	@Test
	public void testForRange() {
		LocalDate min = LocalDate.of(1970, 1, 1);
		LocalDate max = LocalDate.of(2015, 12, 31);
		List<LocalDate> randomDates =Random.localDateGenerator().forRange(min, max).generateList(10);
		for (LocalDate randomDate : randomDates) {
			assertTrue(randomDate.isAfter(min));
			assertTrue(randomDate.isBefore(max));
		}	
	}

	@Test
	public void testForYearsBack() {
		LocalDate now = LocalDate.now();
		int minYearsBack=Random.intGenerator().forRange(10, 20).generate();
		int maxYearsBack=Random.intGenerator().forRange(30, 50).generate();
		List<LocalDate> randomDatesBack = Random.localDateGenerator().forYearsBack(minYearsBack, maxYearsBack).generateList(10);
		for (LocalDate randomDateBack : randomDatesBack) {
			LocalDate min=now.minus(Period.ofYears(maxYearsBack));
			LocalDate max=now.minus(Period.ofYears(minYearsBack));
			assertTrue(randomDateBack.isAfter(min));
			assertTrue(randomDateBack.isBefore(max));
		}
		
		minYearsBack=20;
		maxYearsBack=10;
		LocalDate randomDateBack = Random.localDateGenerator().forYearsBack(minYearsBack, maxYearsBack).generate();
		LocalDate min=now.minus(Period.ofYears(minYearsBack));
		LocalDate max=now.minus(Period.ofYears(maxYearsBack));
		assertTrue(randomDateBack.isAfter(min));
		assertTrue(randomDateBack.isBefore(max));

	}

	@Test
	public void testForYearsForward() {
		LocalDate now = LocalDate.now();
		int minYearsForward=Random.intGenerator().forRange(10, 20).generate();
		int maxYearsForward=Random.intGenerator().forRange(30, 50).generate();
		List<LocalDate> randomDatesForward = Random.localDateGenerator().forYearsForward(minYearsForward, maxYearsForward).generateList(10);
		for (LocalDate randomDateForward : randomDatesForward) {
			LocalDate min=now.plus(Period.ofYears(minYearsForward));
			LocalDate max=now.plus(Period.ofYears(maxYearsForward));
			assertTrue(randomDateForward.isAfter(min));
			assertTrue(randomDateForward.isBefore(max));
		}

		minYearsForward=20;
		maxYearsForward=10;
		LocalDate randomDateForward = Random.localDateGenerator().forYearsForward(minYearsForward, maxYearsForward).generate();
		LocalDate min=now.plus(Period.ofYears(maxYearsForward));
		LocalDate max=now.plus(Period.ofYears(minYearsForward));
		assertTrue(randomDateForward.isAfter(min));
		assertTrue(randomDateForward.isBefore(max));
	}

}
