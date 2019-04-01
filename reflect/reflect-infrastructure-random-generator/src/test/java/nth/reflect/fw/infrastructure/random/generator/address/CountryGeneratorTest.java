package nth.reflect.fw.infrastructure.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class CountryGeneratorTest {

	@Test
	public void testForNorParamaters() {
		List<RandomCountry> allCountries = Resources.countryRepository().getAll();
		List<RandomCountry> randomCountries = Random.country().generateList(1000);
		for (RandomCountry randomCountry : randomCountries) {
			assertValidCountry(randomCountry);
			assertTrue(allCountries.contains(randomCountry));
		}
	}

	private void assertValidCountry(RandomCountry randomCountry) {
		assertThat(randomCountry.getCode()).as("Country code").hasSize(2);
		assertThat(randomCountry.getCode()).as("Country code").isUpperCase();
		assertThat(randomCountry.getName()).as("Country name").isNotBlank();
		assertThat(randomCountry.getPostalCodeFormat()).as("Country postal code format").isNotBlank();
		assertThat(randomCountry.getPhoneCode()).as("Country postal code").isNotBlank();
		assertThat(randomCountry.getPhoneNumberFormat()).as("Phone number format").isNotBlank();
		assertThat(randomCountry.getPhoneDigitsAfterCallingCode()).as("Country phone digits after calling code")
				.isGreaterThan(1);
	}

}
