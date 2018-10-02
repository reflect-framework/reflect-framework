package nth.reflect.fw.infrastructure.random.generator.name;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.hamcrest.number.IsCloseTo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nth.reflect.fw.infrastructure.random.Random;

public class FirstNameGeneratorTest {
	private static final double _100_PERCENT = 100;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private Set<String> allMaleNames;

	@Before
	public void setup() {
		allMaleNames = Random.maleNameGenerator().generateSet(100*1000);
	}

	@Test
	public void testForNoParameters() {
		int size = 100;
		Set<String> randomNames = Random.firstNameGenerator().generateSet(size);
		long maleNameCount = randomNames.stream().filter(name -> allMaleNames.contains(name)).count();
		assertThat(randomNames, hasSize(size));
		double ratio = maleNameCount / (double)size;
		assertThat(ratio, IsCloseTo.closeTo(0.5, 0.2));
	}

	@Test
	public void testForMaleProbability50() {
		testForProbability(50);
	}

	@Test
	public void testForMaleProbability25() {
		testForProbability(25);
	}

	@Test
	public void testForMaleProbability0() {
		testForProbability(0);
	}

	@Test
	public void testForMaleProbability1() {
		testForProbability(1);
	}

	private void testForProbability(int maleProbabilityInPercent) {
		int size = 100;
		Set<String> randomNames = Random.firstNameGenerator().forMaleProbability(maleProbabilityInPercent)
				.generateSet(size);
		long maleNameCount = randomNames.stream().filter(name -> allMaleNames.contains(name)).count();
		assertThat(randomNames, hasSize(size));
		double givenRatio = maleProbabilityInPercent / _100_PERCENT;
		double actualRatio = maleNameCount / (double) size;
		assertThat(actualRatio, IsCloseTo.closeTo(givenRatio, 0.2));
	}

}
