package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random typical English first names for males. Source:
 * https://names.mongabay.com/male_names_alpha.htm
 * 
 * @author nilsth
 *
 */
public class MaleNameGenerator extends FromStringListGenerator {
	public MaleNameGenerator() {
		super(Repositories.maleNameRepository().getMaleNames());
	}
}
