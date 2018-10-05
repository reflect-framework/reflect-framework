package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class DomainNameGenerator extends FromListGenerator<String> {

	public DomainNameGenerator() {
		super(Resources.domainNameRepository());
	}

	
}
