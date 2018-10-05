package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class LoremIpsumGenerator extends FromListGenerator<String> {

	public LoremIpsumGenerator() {
		super(Resources.loremIpsumRepository());
	}

}
