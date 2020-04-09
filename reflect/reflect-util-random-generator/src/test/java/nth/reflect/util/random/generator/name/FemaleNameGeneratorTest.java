package nth.reflect.util.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class FemaleNameGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.femaleName().generateSet(100 * 1000);
		assertThat(result).size().isGreaterThan(999);
		assertThat(result).contains("Cher");
		assertThat(result).contains("Valerie");
	}

}
