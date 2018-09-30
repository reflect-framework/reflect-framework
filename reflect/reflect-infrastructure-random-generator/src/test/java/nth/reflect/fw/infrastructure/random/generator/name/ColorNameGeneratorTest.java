package nth.reflect.fw.infrastructure.random.generator.name;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class ColorNameGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.colorNameGenerator().generateSet(100);
		assertThat(result, hasSize(greaterThan(15)));
		assertThat(result, hasItem("Red"));
		assertThat(result, hasItem("Green"));
		assertThat(result, hasItem("Yellow"));
		assertThat(result, hasItem("Blue"));
		assertThat(result, hasItem("White"));
		assertThat(result, hasItem("Gray"));
	}

}
