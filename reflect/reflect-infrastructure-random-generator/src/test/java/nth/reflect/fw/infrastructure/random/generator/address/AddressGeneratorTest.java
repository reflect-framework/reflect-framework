package nth.reflect.fw.infrastructure.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(addresses).hasSize(size);
		for (RandomAddress address : addresses) {
			assertAddressHasValues(address);
		}
	}

	private void assertAddressHasValues(RandomAddress address) {
		assertThat(address.getStreetName()).as("Address name: %s", address.getStreetName()).isNotBlank();
		assertThat(address.getHouseNumber()).as("Address houseNumber: %s", address.getHouseNumber()).isNotBlank();
		assertThat(address.getPostalCode()).as("Address postal code: %s", address.getPostalCode()).isNotBlank();
		assertThat(address.getCity()).as("Address city: %s", address.getCity()).isNotBlank();
		assertThat(address.getRegion()).as("Address region: %s", address.getRegion())
				.satisfiesAnyOf(region -> assertThat(region).isNotEmpty(), region -> assertThat(region).isEqualTo(""));
		assertThat(address.getCountry()).as("Address country: %s", address.getCountry()).isNotEmpty();
	}

	@Test
	public void testForCountry() {
		RandomCountry theNetherlands = getTheNetherlands();
		int size = 20;
		List<RandomAddress> addresses = Random.address().forCountry(theNetherlands).generateList(size);
		assertThat(addresses).hasSize(size);
		for (RandomAddress address : addresses) {
			assertAddressHasValues(address);
			assertThat(address.getCountry()).isEqualTo(theNetherlands.getName());

			List<String> regionNames = theNetherlands.getRegions().stream().map(r -> r.getName())
					.collect(Collectors.toList());
			assertThat(address.getRegion()).isIn(regionNames);

			Optional<RandomRegion> region = theNetherlands.getRegions().stream()
					.filter(r -> r.getName().equals(address.getRegion())).findFirst();
			assertThat(region.isPresent()).isEqualTo(true);

			List<String> cityNames = region.get().getCities().stream().map(r -> r.getName())
					.collect(Collectors.toList());
			assertThat(address.getCity()).isIn(cityNames);
		}
	}

	private RandomCountry getTheNetherlands() {
		return Resources.countryRepository().getAll().stream().filter(c -> c.getCode().equals("NL")).findFirst().get();
	}

}
