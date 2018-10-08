package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;


public class CityGeneratorTest {

	private static final int NR_OF_REGIONS_IN_THE_NETHERLANDS = 12;
	private List<RandomCity> allCities;

	@Before
	public void init() {
		allCities=Resources.countryRepository().getAllKnowCities();
	}

	
	
	@Test
	public void testForNoParamaters() {
		Set<RandomCity> randomCity = Random.city().generateSet(500);
		assertThat(randomCity, hasSize(500));
		assertThat(randomCity, hasItem(isIn(allCities)));
	}

	@Test
	public void testForCountry() {
		RandomCountry netherlands=getTheNetherlands();
		Set<RandomCity> randomCities = Random.city().forCountry(netherlands).generateSet(50);
		List<RandomCity> citiesInTheNetherlands = getCities(netherlands);
		assertThat(randomCities, hasSize(greaterThan(NR_OF_REGIONS_IN_THE_NETHERLANDS)));
		assertThat(randomCities, hasItem(isIn(citiesInTheNetherlands)));
	}



	private List<RandomCity> getCities(RandomCountry netherlands) {
		List<RandomCity> citiesInTheNetherlands=new ArrayList<>();
		for (RandomRegion randomRegion : netherlands.getRegions()) {
			citiesInTheNetherlands.addAll(randomRegion.getCities());
		}
		return citiesInTheNetherlands;
	}



	private RandomCountry getTheNetherlands() {
		return Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
	}

}
