package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.List;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CountryGenerator extends FromListGenerator<Country> {

	public CountryGenerator() {
		super(Resources.countryRepository());
	}

	public CountryGenerator(List<Country> countries) {
		super(countries);
	}

	public CountryGenerator forCountriesWithKnownCities() {
		return new CountryGenerator(Resources.countryRepository().getWithAllKnownCities());
	}
}
