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
		 List<RandomCountry> allCountries = Resources.countryRepository().getAll();
		List<RandomCountry> randomCountries = Random.country().generateList(1000);
		for (RandomCountry randomCountry : randomCountries) {
			assertValidCountry(randomCountry);
			assertTrue(allCountries.contains(randomCountry));
		}
	}

	private void assertValidCountry(RandomCountry randomCountry) {
		assertThat(randomCountry.getCode(), lambdaMatcher(code -> code.length()==2,"Only 2 characters"));
		assertThat(randomCountry.getCode(), lambdaMatcher(code -> Character.isUpperCase(code.charAt(0))));
		assertThat(randomCountry.getCode(), lambdaMatcher(code -> Character.isUpperCase(code.charAt(1))));
		assertThat(randomCountry.getName(), not(isEmptyOrNullString()));
		assertThat(randomCountry.getPostalCodeFormat(),not(isEmptyOrNullString()));
		assertThat(randomCountry.getPhoneCode(),not(isEmptyOrNullString()));
		assertThat(randomCountry.getPhoneNumberFormat(),not(isEmptyOrNullString()));
		assertThat(randomCountry.getPhoneDigitsAfterCallingCode(), greaterThan(1));
	}
	
	
}
