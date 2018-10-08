package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class RegionGenerator extends FromListGenerator<Region> {

	public RegionGenerator() {
		super (getAllRegions());
	}
	
	private static List<Region> getAllRegions() {
		List<Region> allRegions = new ArrayList<>();
		List<Country> countriesWithRegions = Resources.countryRepository().getWithAllKnownRegions();
		for (Country country : countriesWithRegions) {
			allRegions.addAll(country.getRegions());
		}
		return allRegions;
	}
	
	
	public RegionGenerator(Country country) {
		super (new ArrayList<>(country.getRegions()));
	}

	public RegionGenerator forCountry(Country country) {
		return new RegionGenerator(country);
	}
	
	

}
