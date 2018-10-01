package nth.reflect.fw.infrastructure.random.generator.word;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;
import nth.reflect.fw.infrastructure.random.util.StringUtil;

public class ParagraphGeneratorTest {

	private static CharacterSet SENTENCE_DELIMITERS = CharacterSet.fromString(".!?");


	@Test
	public void testForNoParameters() {
		List<String> paragraphs = Random.paragraphGenerator().generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = StringUtil.countMatches(paragraph,SENTENCE_DELIMITERS)+1;
			assertThat(nrOfSentences, greaterThanOrEqualTo(ParagraphGenerator.MIN_SENTENCES_DEFAULT));
			assertThat(nrOfSentences, lessThanOrEqualTo(ParagraphGenerator.MAX_SENTENCES_DEFAULT));
		}
	}

	@Test
	public void testForSentenceGenerator() {
		SentenceGenerator sentenceGenerator = Random.sentenceGenerator().forNumberOfWords(1);
		List<String> paragraphs = Random.paragraphGenerator().forSentenceGenerator(sentenceGenerator)
				.forSentenceSeperator("").generateList(100);
		for (String paragraph : paragraphs) {
			assertEquals(0, StringUtil.countMatches(paragraph, " "));
		}
	}

	@Test
	public void testForNumberOfSentencesInt() {
		int expectedNrOfSentences = 100;
		List<String> paragraphs = Random.paragraphGenerator().forNumberOfSentences(expectedNrOfSentences)
				.generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = StringUtil.countMatches(paragraph,SENTENCE_DELIMITERS);
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
			int nrOfSentences = StringUtil.countMatches(paragraph,SENTENCE_DELIMITERS);
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
			assertEquals(expectednumberOfSentences,StringUtil.countMatches(paragraph, sentenceSeperator)+1);
		}
	}

}
