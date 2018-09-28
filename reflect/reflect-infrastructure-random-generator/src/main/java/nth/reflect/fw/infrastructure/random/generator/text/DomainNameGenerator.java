package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class DomainNameGenerator extends FromStringListGenerator {
	public DomainNameGenerator() {
		super(new ResourceFile("DomainNames.txt"));
	}
}
