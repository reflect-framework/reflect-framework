package nth.reflect.fw.infrastructure.random.generator.word;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;

public class ParagraphGeneratorTest {

	private static final String SENTENCE_DELIMITERS = ".!?";
	private static final String REG_EXP_SPACES = "\\s+";


	@Test
	public void testForNoParameters() {
		int minSentences = 5;
		int maxSentences = 25;
		List<String> paragraphs = Random.paragraphGenerator().generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = countCharacters(paragraph,SENTENCE_DELIMITERS);
			assertThat(nrOfSentences, greaterThanOrEqualTo(minSentences));
			assertThat(nrOfSentences, lessThanOrEqualTo(maxSentences));
		}
	}

	private int countCharacters(String characters, String charactersToFind) {
		int count = 0;
		for (Character character : characters.toCharArray()) {
			if (charactersToFind.contains(String.valueOf(character))) {
				count++;
			}
		}
		return count;
	}

	@Test
	public void testForSentenceGenerator() {
		SentenceGenerator sentenceGenerator = Random.sentenceGenerator().forNumberOfWords(1);
		List<String> paragraphs = Random.paragraphGenerator().forSentenceGenerator(sentenceGenerator)
				.forSentenceSeperator("").generateList(100);
		for (String paragraph : paragraphs) {
			assertEquals(0, countCharacters(paragraph, " "));
		}
	}

	@Test
	public void testForNumberOfSentencesInt() {
		int expectedNrOfSentences = 100;
		List<String> paragraphs = Random.paragraphGenerator().forNumberOfSentences(expectedNrOfSentences)
				.generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = countCharacters(paragraph,SENTENCE_DELIMITERS);
			assertThat(nrOfSentences, equalTo(expectedNrOfSentences));
		}
	}

	@Test
	public void testForNumberOfSentencesIntInt() {
		int minSentences = 10;
		int maxSentences = 100;
		List<String> paragraphs = Random.paragraphGenerator().forNumberOfSentences(minSentences, maxSentences)
				.generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = countCharacters(paragraph,SENTENCE_DELIMITERS);
			assertThat(nrOfSentences, greaterThanOrEqualTo(minSentences));
			assertThat(nrOfSentences, lessThanOrEqualTo(maxSentences));
		}
	}

	@Test
	public void testForSentenceSeperator() {
		String sentenceSeperator = "-";
		int expectednumberOfSentences = 10;
		List<String> paragraphs = Random.paragraphGenerator().forSentenceSeperator(sentenceSeperator)
				.forNumberOfSentences(expectednumberOfSentences).generateList(100);
		for (String paragraph : paragraphs) {
			assertEquals(expectednumberOfSentences,countCharacters(paragraph, sentenceSeperator)+1);
		}
	}

}
