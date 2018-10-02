package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class DomainNameGenerator extends FromStringListGenerator {
	public DomainNameGenerator() {
		super(Repositories.domainNameRepository().getDomainNames());
	}
}
