package nth.reflect.fw.infrastructure.random.generator.name;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class PersonNameGeneratorTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testPersonNameGenerator() {
		Set<String> personNames = Random.personNameGenerator().generateSet(1000);
		assertThat(personNames,hasSize(greaterThan(900)));
		assertThat(personNames,hasItems(containsString(" ")));
	}

}
