package nth.reflect.fw.infrastructure.random.generator.name;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CompanyNameGenerator extends FromListGenerator<String> {

	public CompanyNameGenerator() {
		super(Resources.companyNameRepository());
	}

}
