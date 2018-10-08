package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CountryRepositoryTest {

	@Test
	public void testMoreThen100Countries() {
		List<Country> countries = Resources.countryRepository().getAll();
		assertThat(countries, hasSize(greaterThan(200)));
	}

	
	@Test
	public void testMoreThen100Regions() {
		List<Country> countries = Resources.countryRepository().getAll();
		long nrOfCountriesWithRegions=countries.stream().filter(country -> !country.getRegions().isEmpty()).count();
		assertThat(nrOfCountriesWithRegions, greaterThan(100l));
	}

	@Test
	public void testAllRegionsHaveCities() {
		List<Country> countries = Resources.countryRepository().getAll();
		for (Country country : countries) {
			long regionsWithoutCities = country.getRegions().stream().filter(r->r.getCities().isEmpty()).count();
			assertThat(regionsWithoutCities, equalTo(0l));
		}
	}

	@Test
	public void testAllWithCities() {
		List<Country> countries = Resources.countryRepository().getCountriesThatHaveCities();
		for (Country country : countries) {
			assertThat(country.getRegions().isEmpty(), is(false));

			long regionsWithoutCities = country.getRegions().stream().filter(r->r.getCities().isEmpty()).count();
			assertThat(regionsWithoutCities, equalTo(0l));
		}
	}

	
}
