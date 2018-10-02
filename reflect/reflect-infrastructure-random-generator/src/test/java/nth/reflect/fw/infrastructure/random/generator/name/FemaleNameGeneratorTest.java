package nth.reflect.fw.infrastructure.random.generator.name;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class FemaleNameGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.femaleNameGenerator().generateSet(100*1000);
		assertThat(result, hasSize(greaterThan(999)));
		assertThat(result, hasItem("Cher"));
		assertThat(result, hasItem("Valerie"));
	}

}
