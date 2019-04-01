package nth.reflect.fw.infrastructure.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LastNameGeneratorTest {

	@Test
	public void testForNoParameters() {
		Set<String> lastNames = Random.lastName().generateSet(100 * 1000);
		assertThat(lastNames).size().isGreaterThan(999);
		assertThat(lastNames).contains("Johnson");
		assertThat(lastNames).contains("Smith");
	}

}
