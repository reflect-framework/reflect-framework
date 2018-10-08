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
	private List<City> allCities;

	@Before
	public void init() {
		allCities=Resources.countryRepository().getAllKnowCities();
	}

	
	
	@Test
	public void testForNoParamaters() {
		Set<City> city = Random.city().generateSet(500);
		assertThat(city, hasSize(500));
		assertThat(city, hasItem(isIn(allCities)));
	}

	@Test
	public void testForCountry() {
		Country netherlands=getTheNetherlands();
		Set<City> cities = Random.city().forCountry(netherlands).generateSet(50);
		List<City> citiesInTheNetherlands = getCities(netherlands);
		assertThat(cities, hasSize(greaterThan(NR_OF_REGIONS_IN_THE_NETHERLANDS)));
		assertThat(cities, hasItem(isIn(citiesInTheNetherlands)));
	}



	private List<City> getCities(Country netherlands) {
		List<City> citiesInTheNetherlands=new ArrayList<>();
		for (Region region : netherlands.getRegions()) {
			citiesInTheNetherlands.addAll(region.getCities());
		}
		return citiesInTheNetherlands;
	}



	private Country getTheNetherlands() {
		return Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
	}

}
