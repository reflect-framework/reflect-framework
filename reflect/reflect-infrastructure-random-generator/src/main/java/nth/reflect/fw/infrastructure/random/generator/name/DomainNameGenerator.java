package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;

public class DomainNameGenerator extends FromStringListGenerator {
	public DomainNameGenerator() {
		super(new ResourceFile("DomainNames.txt"));
	}
}
