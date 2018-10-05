package nth.reflect.fw.infrastructure.random.generator.address;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CountryGenerator extends FromListGenerator<Country>{


	public CountryGenerator() {
		super(Resources.countryRepository());
	}

	
}
