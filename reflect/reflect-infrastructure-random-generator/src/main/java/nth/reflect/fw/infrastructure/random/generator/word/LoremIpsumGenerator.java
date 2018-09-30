package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;

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
