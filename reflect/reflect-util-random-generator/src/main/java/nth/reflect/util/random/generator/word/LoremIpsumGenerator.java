package nth.reflect.util.random.generator.word;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class LoremIpsumGenerator extends FromListGenerator<String> {

	public LoremIpsumGenerator() {
		super(Resources.loremIpsumRepository());
	}

}
