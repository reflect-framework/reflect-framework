package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random list of typical English first names for females. 
 * 
 * @author nilsth
 *
 */
public class FirstNameFemaleGenerator extends FromStringListGenerator {
	public FirstNameFemaleGenerator() {
		super(new ResourceFile("FirstNameFemale.txt"));
	}
}
