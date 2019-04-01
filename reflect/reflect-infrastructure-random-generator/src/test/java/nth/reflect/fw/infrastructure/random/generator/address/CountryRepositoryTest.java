package nth.reflect.fw.infrastructure.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CountryRepositoryTest {

	@Test
	public void testMoreThen100Countries() {
		List<RandomCountry> randomCountries = Resources.countryRepository().getAll();
		assertThat(randomCountries).size().isGreaterThan(200);
	}

	@Test
	public void testMoreThen100Regions() {
		List<RandomCountry> randomCountries = Resources.countryRepository().getAll();
		long nrOfCountriesWithRegions = randomCountries.stream().filter(country -> !country.getRegions().isEmpty())
				.count();
		assertThat(nrOfCountriesWithRegions).isGreaterThan(100l);
	}

	@Test
	public void testAllRegionsHaveCities() {
		List<RandomCountry> randomCountries = Resources.countryRepository().getAll();
		for (RandomCountry randomCountry : randomCountries) {
			long regionsWithoutCities = randomCountry.getRegions().stream().filter(r -> r.getCities().isEmpty())
					.count();
			assertThat(regionsWithoutCities).isEqualTo(0l);
		}
	}

	@Test
	public void testAllWithCities() {
		List<RandomCountry> randomCountries = Resources.countryRepository().getCountriesThatHaveCities();
		for (RandomCountry randomCountry : randomCountries) {
			assertThat(randomCountry.getRegions()).isNotEmpty();
			long regionsWithoutCities = randomCountry.getRegions().stream().filter(r -> r.getCities().isEmpty())
					.count();
			assertThat(regionsWithoutCities).isEqualTo(0l);
		}
	}

}
