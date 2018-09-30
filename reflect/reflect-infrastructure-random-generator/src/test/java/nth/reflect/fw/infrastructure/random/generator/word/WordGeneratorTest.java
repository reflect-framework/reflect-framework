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

	@Test
	public void testNoParameters() {
		int size = 100;
		List<String> words = Random.wordGenerator().generateList(size);
		assertThat(words,hasSize(size));
		for (String word : words) {
			assertThat(word.length(), greaterThan(1*2-1));
			assertThat(word.length(), lessThan(3*3+1));
		}		
	}
	
	@Test
	public void testForLength() {
		int length=Random.intGenerator().forRange(10, 100).generate();
		int size = 100;
		List<String> words = Random.wordGenerator().forLength(length).generateList(size);
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
		List<String> words = Random.wordGenerator().forSyllables(minSyllables, maxSyllables) .generateList(size);
		for (String word : words) {
			assertThat(word.length(), greaterThan(minSyllables*2-1));
			assertThat(word.length(), lessThan(maxSyllables*3+1));
		}		
	}

}
