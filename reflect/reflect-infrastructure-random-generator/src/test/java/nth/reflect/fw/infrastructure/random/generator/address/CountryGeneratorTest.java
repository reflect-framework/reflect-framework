package nth.reflect.fw.infrastructure.random.generator.address;

import static nth.reflect.fw.infrastructure.random.LambdaMatcher.lambdaMatcher;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;
public class CountryGeneratorTest {


	@Test
	public void testForNorParamaters() {
		 List<Country> allCountries = Resources.countryRepository().getAll();
		List<Country> countries = Random.countryGenerator().generateList(1000);
		for (Country country : countries) {
			assertValidCountry(country);
			assertTrue(allCountries.contains(country));
		}
	}

	private void assertValidCountry(Country country) {
		if (country.getCode().length()>2) {
			System.out.println();
		}
		assertThat(country.getCode(), lambdaMatcher(code -> code.length()==2,"Only 2 characters"));
		assertThat(country.getCode(), lambdaMatcher(code -> Character.isUpperCase(code.charAt(0))));
		assertThat(country.getCode(), lambdaMatcher(code -> Character.isUpperCase(code.charAt(1))));
		assertThat(country.getName(), not(isEmptyOrNullString()));
		assertThat(country.getPostalCodeFormat(),not(isEmptyOrNullString()));
		assertThat(country.getPhoneCode(),not(isEmptyOrNullString()));
		assertThat(country.getPhoneNumberFormat(),not(isEmptyOrNullString()));
		assertThat(country.getPhoneDigitsAfterCallingCode(), greaterThan(1));
	}
	
	
}
