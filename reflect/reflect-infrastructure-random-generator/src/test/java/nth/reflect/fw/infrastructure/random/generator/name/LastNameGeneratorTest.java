package nth.reflect.fw.infrastructure.random.generator.name;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LastNameGeneratorTest {

	@Test
	public void testForNoParameters() {
		Set<String> lastNames = Random.lastNameGenerator().generateSet(100*1000);
		assertThat(lastNames, hasSize(greaterThan(999)));
		assertThat(lastNames, hasItem("Johnson"));
		assertThat(lastNames, hasItem("Smith"));
	}

}
