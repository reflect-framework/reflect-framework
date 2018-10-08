package nth.reflect.fw.infrastructure.random.generator.word;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class WordGeneratorTest {

	private static final int MIN_SYLABLES_DEFAULT = 1;
	private static final int MIN_SYLABLES_CHARACTERS_DEFAULT = 2;
	private static final int MAX_SYLABLES_CHARACTERS_DEFAULT = 4;
	private static final int MAX_SYLABLES_DEFAULT = 4;

	@Test
	public void testNoParameters() {
		int size = 100;
		List<String> words = Random.word().generateList(size);
		assertThat(words,hasSize(size));
		for (String word : words) {
			assertThat(word.length(), greaterThan(MIN_SYLABLES_DEFAULT*MIN_SYLABLES_CHARACTERS_DEFAULT-1));
			assertThat(word.length(), lessThan(MAX_SYLABLES_DEFAULT*3+1));
		}		
	}
	
	@Test
	public void testForLength() {
		int length=Random.integer().forRange(10, 100).generate();
		int size = 100;
		List<String> words = Random.word().forLength(length).generateList(size);
		assertThat(words,hasSize(size));
		for (String word : words) {
			assertThat(word.length(), equalTo(length));
		}	
	}

	@Test
	public void testForSyllables() {
		int minSyllables=1;
		int maxSyllables=10;
		int size = 100;
		List<String> words = Random.word().forSyllables(minSyllables, maxSyllables) .generateList(size);
		for (String word : words) {
			assertThat(word.length(), greaterThan(minSyllables*MIN_SYLABLES_CHARACTERS_DEFAULT-1));
			assertThat(word.length(), lessThan(maxSyllables*MAX_SYLABLES_CHARACTERS_DEFAULT+1));
		}		
	}

}
