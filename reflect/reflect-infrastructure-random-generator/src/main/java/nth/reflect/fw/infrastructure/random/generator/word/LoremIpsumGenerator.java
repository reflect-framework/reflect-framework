package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random Lorem Ipsum words. See https://en.wikipedia.org/wiki/Lorem_ipsum
 * @author nilsth
 *
 */
public class LoremIpsumGenerator extends FromStringListGenerator {
	public LoremIpsumGenerator() {
		super(Repositories.loremIpsumGenerator().getLoremIpsumWords());
	}
}
