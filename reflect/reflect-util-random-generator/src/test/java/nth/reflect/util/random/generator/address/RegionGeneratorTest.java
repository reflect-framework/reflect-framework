package nth.reflect.util.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.address.RandomCountry;
import nth.reflect.util.random.generator.address.RandomRegion;
import nth.reflect.util.random.generator.resource.Resources;

public class RegionGeneratorTest {

	private List<RandomRegion> allRegions;

	@Before
	public void init() {
		allRegions = Resources.countryRepository().getAllKnowRegions();
	}

	@Test
	public void testForNoParameters() {
		Set<RandomRegion> randomRegions = Random.region().generateSet(500);
		assertThat(randomRegions).hasSize(500);
		assertThat(randomRegions).containsAnyElementsOf(allRegions);

	}

	@Test
	public void testForCountry() {
		RandomCountry netherlands = Resources.countryRepository().getAll().stream()
				.filter(c -> c.getCode().equals("NL")).findFirst().get();
		Set<RandomRegion> randomRegions = Random.region().forCountry(netherlands).generateSet(50);
		assertThat(randomRegions).hasSize(12);
		assertThat(randomRegions).containsAnyElementsOf(netherlands.getRegions());
	}

}
