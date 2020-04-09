package nth.reflect.util.random.generator.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.ValueGenerator;
import nth.reflect.util.random.generator.collection.ProbabilityGenerator;

public class ProbabilityGeneratorTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private ValueGenerator<Boolean> trueGenerator;
	private ValueGenerator<Boolean> falseGenerator;

	@Before
	public void setup() {
		trueGenerator = new ValueGenerator<>(true);
		falseGenerator = new ValueGenerator<>(false);
	}

	@Test
	public void test50_50() {
		assertProbabilityGeneratorResults(50, 50);
	}

	@Test
	public void test50_150() {
		assertProbabilityGeneratorResults(50, 150);
	}

	@Test
	public void test0_0() {
		exceptionRule.expect(RuntimeException.class);
		assertProbabilityGeneratorResults(0, 0);
	}

	@Test
	public void test0_1() {
		assertProbabilityGeneratorResults(0, 1);
	}

	@Test
	public void test1_0() {
		assertProbabilityGeneratorResults(1, 0);
	}

	@Test
	public void test1_1() {
		assertProbabilityGeneratorResults(1, 1);
	}

	private void assertProbabilityGeneratorResults(int trueProbability, int falseProbability) {
		Map<RandomGenerator<Boolean>, Integer> randomGeneratorsAndProbability = new HashMap<>();
		randomGeneratorsAndProbability.put(trueGenerator, trueProbability);
		randomGeneratorsAndProbability.put(falseGenerator, falseProbability);
		ProbabilityGenerator<Boolean> probabilityGenerator = new ProbabilityGenerator<Boolean>(
				randomGeneratorsAndProbability);
		List<Boolean> randomBooleans = probabilityGenerator.generateList(200);
		double trueCount = randomBooleans.stream().filter(b -> b == true).count();
		double falseCount = randomBooleans.stream().filter(b -> b == false).count();
		if (falseProbability == 0) {
			assertThat(falseCount).isEqualTo(0.0);
		} else {
			double givenRatio = trueProbability / (double) falseProbability;
			double actualRatio = trueCount / falseCount;
			assertThat(actualRatio).isCloseTo(givenRatio, Offset.offset(0.5));
		}
	}

}
