package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.hamcrest.number.IsCloseTo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nth.reflect.fw.infrastructure.random.Random;

public class FirstNameGeneratorTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private Set<String> allMaleNames;
	private Set<String> allFemaleNames;

	@Before
	public void setup() {
		allMaleNames = Random.firstNameMaleGenerator().generateSet(1000);
		allFemaleNames = Random.firstNameFemaleGenerator().generateSet(1000);
	}

	@Test
	public void testForNoParameters() {
		Set<String> randomNames = Random.firstNameGenerator().generateSet(100);
		long maleNameCount = randomNames.stream().filter(name -> allMaleNames.contains(name)).count();
		long femaleNameCount = randomNames.stream().filter(name -> allFemaleNames.contains(name)).count();
		double ratio = maleNameCount / (double) femaleNameCount;
		assertThat(randomNames, hasSize(100));
		assertThat(ratio, IsCloseTo.closeTo(1, 0.5));
	}

	@Test
	public void test50_50() {
		testForProbability(50, 50);
	}

	@Test
	public void test50_150() {
		testForProbability(50, 150);
	}

	@Test
	public void test0_0() {
		exceptionRule.expect(RuntimeException.class);
		testForProbability(0, 0);
	}

	@Test
	public void test0_1() {
		testForProbability(0, 1);
	}

	@Test
	public void test1_0() {
		testForProbability(1, 0);
	}


	private void testForProbability(int maleProbability, int femaleProbability) {
		int size = 50;
		Set<String> randomNames = Random.firstNameGenerator().forProbability(maleProbability, femaleProbability)
				.generateSet(size);
		long maleNameCount = randomNames.stream().filter(name -> allMaleNames.contains(name)).count();
		long femaleNameCount = randomNames.stream().filter(name -> allFemaleNames.contains(name)).count();
		assertThat(randomNames, hasSize(size));
		if (femaleProbability == 0) {
			assertEquals(femaleNameCount,0);
		} else {
			double givenRatio = maleProbability / (double) femaleProbability;
			double actualRatio = maleNameCount / (double) femaleNameCount;
			assertThat(actualRatio, IsCloseTo.closeTo(givenRatio, 0.5));
		}
	}

}
