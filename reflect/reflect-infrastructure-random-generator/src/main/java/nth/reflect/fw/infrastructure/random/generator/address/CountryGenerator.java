package nth.reflect.fw.infrastructure.random.generator.address;

import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;
import nth.reflect.fw.infrastructure.random.generator.collection.FromStringListGenerator;

public class CountryGenerator extends FromStringListGenerator {

	public CountryGenerator() {
		super(Repositories.countryAndPostalCodeRepository().getCountries());
	}
}
