package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class FirstNameFemaleGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.firstNameFemaleGenerator().generateSet(300);
		assertThat(result, hasSize(greaterThan(99)));
		assertThat(result, hasItem("Angel"));
		assertThat(result, hasItem("Zara"));
	}

}
