package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CityGenerator extends FromListGenerator<RandomCity> {

	public CityGenerator() {
		super (Resources.countryRepository().getAllKnowCities());
	}
	
	public CityGenerator(RandomCountry country) {
		super (getKnownCitiesOfCounty(country));
	}

	public CityGenerator(RandomRegion region) {
		super (region.getCities());
	}
	

	private static List<RandomCity> getKnownCitiesOfCounty(RandomCountry country) {
	
		List<RandomCity> knownCities = new ArrayList<>();
		for (RandomRegion randomRegion : country.getRegions()) {
			for (RandomCity randomCity : randomRegion.getCities()) {
				knownCities.add(randomCity);
			}
		}
		return knownCities;
	}

	public CityGenerator forCountry(RandomCountry country) {
		return new CityGenerator(country);
	}

	public CityGenerator forRegion(RandomRegion region) {
		return new CityGenerator(region);
	}

}
