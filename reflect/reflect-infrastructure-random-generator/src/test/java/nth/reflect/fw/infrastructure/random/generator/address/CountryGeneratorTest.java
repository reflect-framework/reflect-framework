package nth.reflect.fw.infrastructure.random.generator.address;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.function.Function;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.hamcrest.text.IsEmptyString;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;
public class CountryGeneratorTest {

	@Test
	public void testForNorParamaters() {
		 List<Country> allCountries = Resources.countryRepository().getAll();
		List<Country> countries = Random.countryGenerator().generateList(100);
		for (Country country : countries) {
			assertValidCountry(country);
			assertTrue(allCountries.contains(country));
		}
	}

	private void assertValidCountry(Country country) {
		assertThat(country.getCode().trim().length(), equalTo(2));
		assertThat(country.getCode(), new LambdaMatcher<>(code -> Character.isUpperCase(code.charAt(0))));
		assertThat(country.getCode(), new LambdaMatcher<>(code -> Character.isUpperCase(code.charAt(1))));
		assertThat(country.getName(), not(isEmptyOrNullString()));
		assertThat(country.getPhoneCode(),not(isEmptyOrNullString()));
		assertThat(country.getPhoneDigitsAfterCallingCode(), greaterThan(1));
	}


	
	
	public class LambdaMatcher<T> extends BaseMatcher<T>
	{
	    private final Function<T, Boolean> matcher;

	    public LambdaMatcher(Function<T, Boolean> matcher)
	    {
	        this.matcher = matcher;
	    }

	    @SuppressWarnings("unchecked")
		@Override
	    public boolean matches(Object argument)
	    {
	        return matcher.apply((T) argument);
	    }

	    @Override
	    public void describeTo(Description description)
	    {
	        description.appendText("A matching lambda expression");
	    }

	}
}
