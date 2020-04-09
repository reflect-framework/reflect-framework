package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class LastNameGenerator extends FromListGenerator<String> {

	public LastNameGenerator() {
		super(Resources.lastNameRepository());
	}

}
