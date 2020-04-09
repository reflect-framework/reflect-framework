package nth.reflect.util.random.generator.address;

import java.util.List;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class CountryGenerator extends FromListGenerator<RandomCountry> {

	public CountryGenerator() {
		super(Resources.countryRepository());
	}

	public CountryGenerator(List<RandomCountry> countries) {
		super(countries);
	}

	public CountryGenerator forCountriesWithKnownCities() {
		return new CountryGenerator(Resources.countryRepository().getCountriesThatHaveCities());
	}
}
