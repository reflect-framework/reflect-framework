package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

/**
 * Generates random Lorem Ipsum words. See https://en.wikipedia.org/wiki/Lorem_ipsum
 * @author nilsth
 *
 */
public class LoremIpsumGenerator extends FromStringListGenerator {
	public LoremIpsumGenerator() {
		super(new ResourceFile("LoremIpsum.txt"));
	}
}
