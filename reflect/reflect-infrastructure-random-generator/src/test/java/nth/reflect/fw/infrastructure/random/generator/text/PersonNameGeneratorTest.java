package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class PersonNameGeneratorTest {

	@Test
	public void testPersonNameGenerator() {
		Set<String> personNames = Random.personNameGenerator().generateSet(1000);
		assertThat(personNames,hasSize(greaterThan(900)));
	}

}
