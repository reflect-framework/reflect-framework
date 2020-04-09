package nth.reflect.util.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.address.RandomCity;
import nth.reflect.util.random.generator.address.RandomCountry;
import nth.reflect.util.random.generator.address.RandomRegion;
import nth.reflect.util.random.generator.resource.Resources;

public class CityGeneratorTest {

	private static final int NR_OF_REGIONS_IN_THE_NETHERLANDS = 12;
	private List<RandomCity> allCities;

	@Before
	public void init() {
		allCities = Resources.countryRepository().getAllKnowCities();
	}

	@Test
	public void testForNoParamaters() {
		Set<RandomCity> randomCity = Random.city().generateSet(500);
		assertThat(randomCity).hasSize(500);
		assertThat(randomCity).containsAnyElementsOf(allCities);
	}

	@Test
	public void testForCountry() {
		RandomCountry netherlands = getTheNetherlands();
		Set<RandomCity> randomCities = Random.city().forCountry(netherlands).generateSet(50);
		List<RandomCity> citiesInTheNetherlands = getCities(netherlands);
		assertThat(randomCities).size().isGreaterThan(NR_OF_REGIONS_IN_THE_NETHERLANDS);
		assertThat(randomCities).containsAnyElementsOf(citiesInTheNetherlands);
	}

	private List<RandomCity> getCities(RandomCountry netherlands) {
		List<RandomCity> citiesInTheNetherlands = new ArrayList<>();
		for (RandomRegion randomRegion : netherlands.getRegions()) {
			citiesInTheNetherlands.addAll(randomRegion.getCities());
		}
		return citiesInTheNetherlands;
	}

	private RandomCountry getTheNetherlands() {
		return Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
	}

}
