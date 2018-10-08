package nth.reflect.fw.infrastructure.random.generator.address;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class StreetNameGenerator extends FromListGenerator<String> {

	public StreetNameGenerator() {
		super(Resources.streetNameRepository());
	}


}
