package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CityGenerator extends FromListGenerator<City> {

	public CityGenerator() {
		super (Resources.countryRepository().getAllKnowCities());
	}
	
	public CityGenerator(Country country) {
		super (getKnownCitiesOfCounty(country));
	}

	private static List<City> getKnownCitiesOfCounty(Country country) {
		List<City> knownCities = new ArrayList<>();
		for (Region region : country.getRegions()) {
			for (City city : region.getCities()) {
				knownCities.add(city);
			}
		}
		return knownCities;
	}

	public CityGenerator forCountry(Country country) {
		return new CityGenerator(country);
	}

}
