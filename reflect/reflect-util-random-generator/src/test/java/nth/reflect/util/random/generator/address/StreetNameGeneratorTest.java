package nth.reflect.util.random.generator.address;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class StreetNameGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<String> streetNames = Random.streetName().generateSet(1000);
		assertThat(streetNames).size().isGreaterThan(380);
		assertThat(streetNames).allSatisfy(streetName -> assertThat(streetName).isNotBlank());
	}

}
