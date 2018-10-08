package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.resource.Resources;

public class AddressGeneratorTest {

	@Test
	public void testForNoParamater() {
		int size = 20;
		List<RandomAddress> addresses = Random.address().generateList(size);
		assertThat(addresses, hasSize(size));
		for (RandomAddress address : addresses) {
			assertAddressHasValues(address);
		}
	}

	private void assertAddressHasValues(RandomAddress address) {
		assertThat(address.getStreetName(), not(isEmptyOrNullString()));
		assertThat(address.getHouseNumber(), not(isEmptyOrNullString()));
		assertThat(address.getPostalCode(), not(isEmptyOrNullString()));
		assertThat(address.getCity(), not(isEmptyOrNullString()));
		assertThat(address.getRegion(), anyOf(is(""), not(isEmptyOrNullString())));
		assertThat(address.getCountry(), not(isEmptyOrNullString()));
	}

	@Test
	public void testForCountry() {
		RandomCountry theNetherlands=getTheNetherlands();
		int size=20;
		List<RandomAddress> addresses = Random.address().forCountry(theNetherlands).generateList(size);
		assertThat(addresses, hasSize(size));
		for (RandomAddress address : addresses) {
			assertAddressHasValues(address);
			assertThat(address.getCountry(), is(theNetherlands.getName()));
			
			List<String> regionNames = theNetherlands.getRegions().stream().map(r ->r.getName()).collect(Collectors.toList());
			assertThat(address.getRegion(), isIn(regionNames));
			
			Optional<RandomRegion> region = theNetherlands.getRegions().stream().filter(r ->r.getName().equals(address.getRegion())).findFirst();
			assertThat(region.isPresent(), is(true) );
			
			List<String> cityNames = region.get().getCities().stream().map(r ->r.getName()).collect(Collectors.toList());
			assertThat(address.getCity(), isIn(cityNames) );
		}
	}

	private RandomCountry getTheNetherlands() {
		return Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
	}

}
