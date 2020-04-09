package nth.reflect.util.random.generator.datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class LocalDateGeneratorTest {
	@Test
	public void testForRange() {
		LocalDate min = LocalDate.of(1970, 1, 1);
		LocalDate max = LocalDate.of(2015, 12, 31);
		List<LocalDate> randomDates = Random.localDate().forRange(min, max).generateList(10);
		assertThat(randomDates).allSatisfy(d -> assertThat(d).isAfter(min).isBefore(max));
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
			assertThat(randomDateBack).isAfter(min).isBefore(max);
		}

		minPeriodBack = Period.ofYears(20);
		maxPeriodBack = Period.ofYears(10);
		LocalDate randomDateBack = Random.localDate().forPeriodInThePast(minPeriodBack, maxPeriodBack).generate();
		LocalDate min = now.minus(minPeriodBack);
		LocalDate max = now.minus(maxPeriodBack);
		assertThat(randomDateBack).isAfter(min).isBefore(max);

	}

	@Test
	public void testForPeriodInTheFuture() {
		LocalDate now = LocalDate.now();
		Period minPeriodForward = Period.ofYears(Random.integer().forRange(10, 20).generate());
		Period maxPeriodForward = Period.ofYears(Random.integer().forRange(30, 50).generate());
		List<LocalDate> randomDatesForward = Random.localDate().forPeriodInTheFuture(minPeriodForward, maxPeriodForward)
				.generateList(10);
		for (LocalDate randomDateForward : randomDatesForward) {
			LocalDate min = now.plus(minPeriodForward);
			LocalDate max = now.plus(maxPeriodForward);
			assertThat(randomDateForward).isAfter(min).isBefore(max);
		}

		minPeriodForward = Period.ofYears(20);
		maxPeriodForward = Period.ofYears(10);
		LocalDate randomDateForward = Random.localDate().forPeriodInTheFuture(minPeriodForward, maxPeriodForward)
				.generate();
		LocalDate min = now.plus(maxPeriodForward);
		LocalDate max = now.plus(minPeriodForward);
		assertThat(randomDateForward).isAfter(min).isBefore(max);
	}

}
