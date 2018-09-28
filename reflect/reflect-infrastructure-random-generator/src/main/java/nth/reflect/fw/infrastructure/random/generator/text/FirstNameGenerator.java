package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;

/**
 * Generates typical English person names, using other {@link RandomGenerator}s
 * 
 * @author nilsth
 *
 */
public class FirstNameGenerator extends StringGenerator {


	private final BoolGenerator isMaleGenerator;
	private final FirstNameFemaleGenerator femaleNameGenerator;
	private final FirstNameMaleGenerator maleNameGenerator;

	public FirstNameGenerator() {
		this(50);
	}

	public FirstNameGenerator(int maleProbabilityInPercent) {
		isMaleGenerator = Random.boolGenerator().forProbability(maleProbabilityInPercent);
		femaleNameGenerator = Random.firstNameFemaleGenerator();
		maleNameGenerator = Random.firstNameMaleGenerator();
	}

	public FirstNameGenerator forMaleProbability(int maleProbabilityInPercent) {
		return new FirstNameGenerator(maleProbabilityInPercent);
	}

	@Override
	public String generate() {
		if (isMaleGenerator.generate()) {
			return maleNameGenerator.generate();
		} else {
			return femaleNameGenerator.generate();
		}
	}

}