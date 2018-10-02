package nth.reflect.fw.infrastructure.random.generator.address;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.csv.repository.Repositories;

public class CountryGeneratorTest {

	@Test
	public void testForNorParamaters() {
		List<String> allCountries = Repositories.countryAndPostalCodeRepository().getCountries();
		String country = Random.countryGenerator().generate();
		assertTrue(allCountries.contains(country));
	}

}
