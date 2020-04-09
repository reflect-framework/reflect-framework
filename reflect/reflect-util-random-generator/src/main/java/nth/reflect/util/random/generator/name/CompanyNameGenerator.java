package nth.reflect.util.random.generator.name;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class CompanyNameGenerator extends FromListGenerator<String> {

	public CompanyNameGenerator() {
		super(Resources.companyNameRepository());
	}

}
