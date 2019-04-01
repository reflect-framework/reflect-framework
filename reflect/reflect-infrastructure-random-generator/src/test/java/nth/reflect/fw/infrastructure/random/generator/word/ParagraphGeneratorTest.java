package nth.reflect.fw.infrastructure.random.generator.word;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.text.CharacterSet;
import nth.reflect.fw.infrastructure.random.generator.util.StringUtil;

public class ParagraphGeneratorTest {

	private static CharacterSet SENTENCE_DELIMITERS = CharacterSet.fromString(".!?");

	@Test
	public void testForNoParameters() {
		List<String> paragraphs = Random.paragraph().generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = StringUtil.countMatches(paragraph, SENTENCE_DELIMITERS) + 1;
			assertThat(nrOfSentences).isGreaterThanOrEqualTo(ParagraphGenerator.MIN_SENTENCES_DEFAULT);
			assertThat(nrOfSentences).isLessThanOrEqualTo(ParagraphGenerator.MAX_SENTENCES_DEFAULT);
		}
	}

	@Test
	public void testForSentenceGenerator() {
		SentenceGenerator sentenceGenerator = Random.sentence().forNumberOfWords(1);
		List<String> paragraphs = Random.paragraph().forSentenceGenerator(sentenceGenerator).forSentenceSeperator("")
				.generateList(100);
		for (String paragraph : paragraphs) {
			assertEquals(0, StringUtil.countMatches(paragraph, " "));
		}
	}

	@Test
	public void testForNumberOfSentencesInt() {
		int expectedNrOfSentences = 100;
		List<String> paragraphs = Random.paragraph().forNumberOfSentences(expectedNrOfSentences).generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = StringUtil.countMatches(paragraph, SENTENCE_DELIMITERS);
			assertThat(nrOfSentences).isEqualTo(expectedNrOfSentences);
		}
	}

	@Test
	public void testForNumberOfSentencesIntInt() {
		int minSentences = 10;
		int maxSentences = 100;
		List<String> paragraphs = Random.paragraph().forNumberOfSentences(minSentences, maxSentences).generateList(100);
		for (String paragraph : paragraphs) {
			int nrOfSentences = StringUtil.countMatches(paragraph, SENTENCE_DELIMITERS);
			assertThat(nrOfSentences).isGreaterThanOrEqualTo(minSentences);
			assertThat(nrOfSentences).isLessThanOrEqualTo(maxSentences);
		}
	}

	@Test
	public void testForSentenceSeperator() {
		String sentenceSeperator = "-";
		int expectednumberOfSentences = 10;
		List<String> paragraphs = Random.paragraph().forSentenceSeperator(sentenceSeperator)
				.forNumberOfSentences(expectednumberOfSentences).generateList(100);
		for (String paragraph : paragraphs) {
			int actualNumberOfSentences = StringUtil.countMatches(paragraph, sentenceSeperator) + 1;
			assertThat(actualNumberOfSentences).isEqualTo(expectednumberOfSentences);
		}
	}

}
