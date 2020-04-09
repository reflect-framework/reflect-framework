package nth.reflect.util.random.generator.address;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class StreetNameGenerator extends FromListGenerator<String> {

	public StreetNameGenerator() {
		super(Resources.streetNameRepository());
	}


}
