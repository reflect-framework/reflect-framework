package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random typical English first names for males. Source:
 * https://names.mongabay.com/male_names_alpha.htm
 * 
 * @author nilsth
 *
 */
public class FirstNameMaleGenerator extends FromStringListGenerator {
	public FirstNameMaleGenerator() {
		super(new ResourceFile("FirstNameMale.txt"));
	}
}
