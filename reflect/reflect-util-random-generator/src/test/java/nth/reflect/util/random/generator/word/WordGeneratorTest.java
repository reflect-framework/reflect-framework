package nth.reflect.util.random.generator.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.util.random.Random;

public class WordGeneratorTest {

	private static final int MIN_SYLABLES_DEFAULT = 1;
	private static final int MIN_SYLABLES_CHARACTERS_DEFAULT = 2;
	private static final int MAX_SYLABLES_CHARACTERS_DEFAULT = 4;
	private static final int MAX_SYLABLES_DEFAULT = 4;

	@Test
	public void testNoParameters() {
		int size = 100;
		List<String> words = Random.word().generateList(size);
		assertThat(words).hasSize(size);
		for (String word : words) {
			assertThat(word).hasSizeGreaterThan(MIN_SYLABLES_DEFAULT * MIN_SYLABLES_CHARACTERS_DEFAULT - 1);
			assertThat(word).hasSizeLessThan(MAX_SYLABLES_DEFAULT * 3 + 1);
		}
	}

	@Test
	public void testForLength() {
		int length = Random.integer().forRange(10, 100).generate();
		int size = 100;
		List<String> words = Random.word().forLength(length).generateList(size);
		assertThat(words).hasSize(size);
		for (String word : words) {
			assertThat(word).hasSize(length);
		}
	}

	@Test
	public void testForSyllables() {
		int minSyllables = 1;
		int maxSyllables = 10;
		int size = 100;
		List<String> words = Random.word().forSyllables(minSyllables, maxSyllables).generateList(size);
		for (String word : words) {
			assertThat(word).hasSizeGreaterThan(minSyllables * MIN_SYLABLES_CHARACTERS_DEFAULT - 1);
			assertThat(word).hasSizeLessThan(maxSyllables * MAX_SYLABLES_CHARACTERS_DEFAULT + 1);
		}
	}

}
