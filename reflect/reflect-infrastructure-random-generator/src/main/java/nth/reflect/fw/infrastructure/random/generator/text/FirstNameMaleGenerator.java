package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random list of typical English first names for males. Source:
 * http://www.babynamewizard.com/name-list/english-boys-names-most-popular-names-for-boys-in-england
 * 
 * @author nilsth
 *
 */
public class FirstNameMaleGenerator extends FromStringListGenerator {
	public FirstNameMaleGenerator() {
		super(new ResourceFile("FirstNameMale.txt"));
	}
}
