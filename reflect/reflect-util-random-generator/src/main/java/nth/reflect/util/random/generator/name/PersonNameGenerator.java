package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;
import nth.reflect.util.random.generator.number.BoolGenerator;

/**
 * Generates random list of typical English names, using
 * 
 * @author nilsth
 *
 */
public class PersonNameGenerator extends RandomGenerator<String> {
	private static final String SPACE = " ";
	private final BoolGenerator isMaleGenerator;
	private final FemaleNameGenerator femaleNameGenerator;
	private final MaleNameGenerator maleNameGenerator;
	private final int maleProbabilityInPercent;
	private final int middleNameProbabilityPercent;
	private final BoolGenerator hasMiddleNameGenerator;
	private final LastNameGenerator lastNameGenerator;

	public PersonNameGenerator() {
		this(50, 30);
	}

	public PersonNameGenerator(int maleProbabilityInPercent, int middleNameProbabilityPercent) {
		this.maleProbabilityInPercent = maleProbabilityInPercent;
		this.middleNameProbabilityPercent = middleNameProbabilityPercent;
		isMaleGenerator = Random.bool().forProbability(maleProbabilityInPercent);
		hasMiddleNameGenerator = Random.bool().forProbability(maleProbabilityInPercent);
		femaleNameGenerator = Random.femaleName();
		maleNameGenerator = Random.maleName();
		lastNameGenerator = Random.lastName();
	}

	public PersonNameGenerator forMaleProbability(int maleProbabilityInPercent) {
		return new PersonNameGenerator(maleProbabilityInPercent, middleNameProbabilityPercent);
	}

	public PersonNameGenerator forMiddleNameProbability(int middleNameProbabilityPercent) {
		return new PersonNameGenerator(maleProbabilityInPercent, middleNameProbabilityPercent);
	}

	@Override
	public String generate() {
		StringBuilder fullName = new StringBuilder();

		appendFirstName(fullName);

		if (hasMiddleNameGenerator.generate()) {
			fullName.append(SPACE);
			// append first name as middle name
			appendFirstName(fullName);
		}

		fullName.append(SPACE);
		fullName.append(lastNameGenerator.generate());

		return fullName.toString();
	}

	private void appendFirstName(StringBuilder fullName) {
		if (isMaleGenerator.generate()) {
			fullName.append(maleNameGenerator.generate());
		} else {
			fullName.append(femaleNameGenerator.generate());
		}
	}}
