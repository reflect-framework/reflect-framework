package nth.reflect.util.random.generator.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class LoremIpsumGeneratorTest {

	@Test
	public void testGenerate() {
		Set<String> result = Random.loremIpsum().generateSet(200);
		assertThat(result).hasSizeGreaterThan(60);
		assertThat(result).contains("lorem");
		assertThat(result).contains("ipsum");
	}

}
