package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;

/**
 * Generates typical English person names, using other {@link RandomGenerator}s
 * 
 * @author nilsth
 *
 */
public class FirstNameGenerator extends RandomGenerator<String> {


	private final BoolGenerator isMaleGenerator;
	private final FemaleNameGenerator femaleNameGenerator;
	private final MaleNameGenerator maleNameGenerator;

	public FirstNameGenerator() {
		this(50);
	}

	public FirstNameGenerator(int maleProbabilityInPercent) {
		isMaleGenerator = Random.bool().forProbability(maleProbabilityInPercent);
		femaleNameGenerator = Random.femaleName();
		maleNameGenerator = Random.maleName();
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