package nth.reflect.fw.infrastructure.random.generator.text;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class LoremIpsumGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.loremIpsumGenerator().generateSet(200);
		assertThat(result, hasSize(greaterThan(60)));
		assertThat(result, hasItem("lorem"));
		assertThat(result, hasItem("ipsum"));
	}

}
