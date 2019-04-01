package nth.reflect.fw.infrastructure.random.generator.name;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class MaleNameGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.maleName().generateSet(100000);
		assertThat(result).size().isGreaterThan(999);
		assertThat(result).contains("Josh");
		assertThat(result).contains("Jamie");
	}

}
