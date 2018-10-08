package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class RegionGeneratorTest {

	private List<Region> allRegions;
	
	@Before
	public void init() {
		allRegions=getAllRegions();
	}

	@Test
	public void testForNoParameters() {
		Set<Region> regions = Random.region().generateSet(500);
		assertThat(regions, hasSize(500));
		assertThat(regions, hasItem(isIn(allRegions)));
		
	}
	
	private static List<Region> getAllRegions() {
		List<Region> allRegions = new ArrayList<>();
		List<Country> countriesWithRegions = Resources.countryRepository().getWithAllKnownRegions();
		for (Country country : countriesWithRegions) {
			allRegions.addAll(country.getRegions());
		}
		return allRegions;
	}

	@Test
	public void testForCountry() {
		Country netherlands=Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
		Set<Region> regions = Random.region().forCountry(netherlands).generateSet(50);
		assertThat(regions, hasSize(12));
		assertThat(regions, hasItem(isIn(netherlands.getRegions())));
	}

}
