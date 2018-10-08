package nth.reflect.fw.infrastructure.random.generator.address;

import java.util.ArrayList;

import nth.reflect.fw.infrastructure.random.generator.collection.FromListGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class RegionGenerator extends FromListGenerator<Region> {

	public RegionGenerator() {
		super (Resources.countryRepository().getAllKnowRegions());
	}
	
	public RegionGenerator(Country country) {
		super (new ArrayList<>(country.getRegions()));
	}

	public RegionGenerator forCountry(Country country) {
		return new RegionGenerator(country);
	}
	
	

}
