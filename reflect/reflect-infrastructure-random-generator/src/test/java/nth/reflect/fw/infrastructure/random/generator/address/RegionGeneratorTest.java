package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class RegionGeneratorTest {

	private List<RandomRegion> allRegions;
	
	@Before
	public void init() {
		allRegions=Resources.countryRepository().getAllKnowRegions();
	}

	@Test
	public void testForNoParameters() {
		Set<RandomRegion> randomRegions = Random.region().generateSet(500);
		assertThat(randomRegions, hasSize(500));
		assertThat(randomRegions, hasItem(isIn(allRegions)));
		
	}
	
	@Test
	public void testForCountry() {
		RandomCountry netherlands=Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
		Set<RandomRegion> randomRegions = Random.region().forCountry(netherlands).generateSet(50);
		assertThat(randomRegions, hasSize(12));
		assertThat(randomRegions, hasItem(isIn(netherlands.getRegions())));
	}

}
