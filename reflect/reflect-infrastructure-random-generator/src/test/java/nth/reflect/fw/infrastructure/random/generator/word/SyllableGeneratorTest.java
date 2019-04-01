package nth.reflect.fw.infrastructure.random.generator.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class SyllableGeneratorTest {

	@Test
	public void testSyllableGenerator() {
		List<String> syllables = Random.syllable().generateList(100);
		for (String syllable : syllables) {
			assertThat(syllable).hasSizeLessThanOrEqualTo(4);
			assertThat(syllable).hasSizeGreaterThanOrEqualTo(2);
		}
	}

}
