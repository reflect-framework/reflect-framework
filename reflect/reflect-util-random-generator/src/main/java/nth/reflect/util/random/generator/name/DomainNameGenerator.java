package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class DomainNameGenerator extends FromListGenerator<String> {

	public DomainNameGenerator() {
		super(Resources.domainNameRepository());
	}

	
}
