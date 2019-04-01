package nth.reflect.fw.infrastructure.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class PersonNameGeneratorTest {

	@Test
	public void testPersonNameGenerator() {
		Set<String> personNames = Random.personName().generateSet(1000);
		assertThat(personNames).size().isGreaterThan(900);
		assertThat(personNames).allSatisfy(name -> assertThat(name).contains(" "));
	}

}
