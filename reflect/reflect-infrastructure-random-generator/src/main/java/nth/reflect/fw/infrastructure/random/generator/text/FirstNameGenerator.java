package nth.reflect.fw.infrastructure.random.generator.text;

import java.util.HashMap;
import java.util.Map;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.ProbabilityGenerator;

/**
 * Generates random list of typical English first names for females. 
 * 
 * @author nilsth
 *
 */
public class FirstNameGenerator extends StringGenerator {
	private final ProbabilityGenerator<String> probabilityGenerator;

	public FirstNameGenerator() {
		this(50,50);
	}
	
	public FirstNameGenerator(int maleProbability, int femaleProbability) {
		Map<RandomGenerator<String>, Integer> randomGeneratorsAndProbability=new HashMap<>();
		randomGeneratorsAndProbability.put(new FirstNameMaleGenerator(), maleProbability);
		randomGeneratorsAndProbability.put(new FirstNameFemaleGenerator(), femaleProbability);
		probabilityGenerator=new ProbabilityGenerator<String>(randomGeneratorsAndProbability);
	}

	public FirstNameGenerator forProbability(int maleProbability, int femaleProbability)  {
		return new FirstNameGenerator(maleProbability, femaleProbability);
	}
	
	@Override
	public String generate() {
		return probabilityGenerator.generate();
	}
}
