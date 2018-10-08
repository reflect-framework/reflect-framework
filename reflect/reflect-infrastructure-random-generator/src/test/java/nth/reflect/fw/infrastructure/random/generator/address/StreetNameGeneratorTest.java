package nth.reflect.fw.infrastructure.random.generator.address;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class StreetNameGeneratorTest {

	@Test
	public void testForNoParameter() {
		Set<String> streetNames = Random.streetName().generateSet(1000);
		assertThat(streetNames, hasSize(greaterThan(380)));
		assertThat(streetNames, hasItem(not(isEmptyOrNullString())));
	}

}
