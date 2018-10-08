package nth.reflect.fw.infrastructure.random.generator.datetime;

import static org.junit.Assert.assertTrue;

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
		List<LocalDate> randomDates = Random.localDate().forRange(min, max).generateList(10);
		for (LocalDate randomDate : randomDates) {
			assertTrue(randomDate.isAfter(min));
			assertTrue(randomDate.isBefore(max));
		}
	}

	@Test
	public void testForPeriodInThePast() {
		LocalDate now = LocalDate.now();
		Period minPeriodBack = Period.ofYears(Random.integer().forRange(10, 20).generate());
		Period maxPeriodBack = Period.ofYears(Random.integer().forRange(30, 50).generate());
		List<LocalDate> randomDatesBack = Random.localDate().forPeriodInThePast(minPeriodBack, maxPeriodBack)
				.generateList(10);
		for (LocalDate randomDateBack : randomDatesBack) {
			LocalDate min = now.minus(maxPeriodBack);
			LocalDate max = now.minus(minPeriodBack);
			assertTrue(randomDateBack.isAfter(min));
			assertTrue(randomDateBack.isBefore(max));
		}

		minPeriodBack = Period.ofYears(20);
		maxPeriodBack = Period.ofYears(10);
		LocalDate randomDateBack = Random.localDate().forPeriodInThePast(minPeriodBack, maxPeriodBack)
				.generate();
		LocalDate min = now.minus(minPeriodBack);
		LocalDate max = now.minus(maxPeriodBack);
		assertTrue(randomDateBack.isAfter(min));
		assertTrue(randomDateBack.isBefore(max));

	}

	@Test
	public void testForPeriodInTheFuture() {
		LocalDate now = LocalDate.now();
		Period minPeriodForward = Period.ofYears(Random.integer().forRange(10, 20).generate());
		Period maxPeriodForward = Period.ofYears(Random.integer().forRange(30, 50).generate());
		List<LocalDate> randomDatesForward = Random.localDate()
				.forPeriodInTheFuture(minPeriodForward, maxPeriodForward).generateList(10);
		for (LocalDate randomDateForward : randomDatesForward) {
			LocalDate min = now.plus(minPeriodForward);
			LocalDate max = now.plus(maxPeriodForward);
			assertTrue(randomDateForward.isAfter(min));
			assertTrue(randomDateForward.isBefore(max));
		}

		minPeriodForward = Period.ofYears(20);
		maxPeriodForward = Period.ofYears(10);
		LocalDate randomDateForward = Random.localDate()
				.forPeriodInTheFuture(minPeriodForward, maxPeriodForward).generate();
		LocalDate min = now.plus(maxPeriodForward);
		LocalDate max = now.plus(minPeriodForward);
		assertTrue(randomDateForward.isAfter(min));
		assertTrue(randomDateForward.isBefore(max));
	}

}
