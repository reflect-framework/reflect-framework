package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;

/**
 * Generates random typical English first names for females. Source:
 * https://names.mongabay.com/female_names_alpha.htm
 * 
 * @author nilsth
 *
 */
public class FirstNameFemaleGenerator extends FromStringListGenerator {
	public FirstNameFemaleGenerator() {
		super(new ResourceFile("FirstNameFemale.txt"));
	}
}
