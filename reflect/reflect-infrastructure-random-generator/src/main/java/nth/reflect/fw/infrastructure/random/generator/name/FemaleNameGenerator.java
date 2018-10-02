package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random typical English first names for females. Source:
 * https://names.mongabay.com/female_names_alpha.htm
 * 
 * @author nilsth
 *
 */
public class FemaleNameGenerator extends FromStringListGenerator {
	public FemaleNameGenerator() {
		super(Repositories.femaleNameRepository().getFemaleNames());
	}
}
