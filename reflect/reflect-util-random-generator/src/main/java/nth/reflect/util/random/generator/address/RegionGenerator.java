package nth.reflect.util.random.generator.address;

import java.util.ArrayList;

import nth.reflect.util.random.generator.collection.FromListGenerator;
import nth.reflect.util.random.generator.resource.Resources;

public class RegionGenerator extends FromListGenerator<RandomRegion> {

	public RegionGenerator() {
		super (Resources.countryRepository().getAllKnowRegions());
	}
	
	public RegionGenerator(RandomCountry country) {
		super (new ArrayList<>(country.getRegions()));
	}

	public RegionGenerator forCountry(RandomCountry country) {
		return new RegionGenerator(country);
	}
	
	

}
