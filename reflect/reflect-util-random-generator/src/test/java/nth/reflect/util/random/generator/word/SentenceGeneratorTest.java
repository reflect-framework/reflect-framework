package nth.reflect.util.random.generator.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.generator.word.SentenceGenerator;

public class SentenceGeneratorTest {
	private static final List<Character> END_DELIMITERS = Arrays.asList('.', '!', '?');
	private static final String REG_EXP_SPACES = "\\s+";
	private Set<String> loremIpsumWords;

	@Before
	public void init() {
		loremIpsumWords = Random.loremIpsum().generateSet(1000);
	}

	@Test
	public void testForNoParameters() {
		int expectMinWords = 7;
		int expectMaxWords = 15;
		boolean expectEndDelimiter = true;
		boolean expectLoremIpsum = false;
		SentenceGenerator sentenceGenerator = Random.sentence();
		assertResult(sentenceGenerator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);
	}

	private void assertResult(SentenceGenerator sentenceGenerator, int expectMinWords, int expectMaxWords,
			boolean expectEndDelimiter, boolean expectLoremIpsum) {
		List<String> sentences = sentenceGenerator.generateList(100);
		for (String sentence : sentences) {
			Character lastChar = sentence.charAt(sentence.length() - 1);
			if (expectEndDelimiter) {
				assertThat(lastChar).isIn(END_DELIMITERS).overridingErrorMessage("Sentence must end with delimeter.");
				sentence = sentence.substring(0, sentence.length() - 1);
			} else {
				assertThat(lastChar).isNotIn(END_DELIMITERS)
						.overridingErrorMessage("Sentence may not end with delimeter.");
			}
			if (sentence.length() > 0 || expectMinWords > 0) {
				String[] words = sentence.trim().split(REG_EXP_SPACES);
				assertThat(words).as("Minimum number of words").hasSizeGreaterThan(expectMinWords - 1);
				assertThat(words).as("Maximum number of words").hasSizeLessThan(expectMaxWords + 1);
				assertThat(Arrays.asList(words)).overridingErrorMessage("May not contain empty words")
						.allSatisfy(word -> assertThat(word).isNotBlank());
				assertThat(sentence.charAt(0)).overridingErrorMessage("Sentence must start with capital letter.")
						.isUpperCase();

				if (expectLoremIpsum) {
					assertLoremIpsumWords(words);
				}
			}

		}
	}

	private void assertLoremIpsumWords(String[] words) {
		for (String word : words) {
			String normilizedWord = word.toLowerCase().replace(".", "").replace("!", "").replace("?", "");
			assertThat(normilizedWord).isIn(loremIpsumWords).overridingErrorMessage("Must be a Lorem Ipsum word");
		}
	}

	@Test
	public void testForLoremIpsum() {
		int expectMinWords = 7;
		int expectMaxWords = 15;
		boolean expectEndDelimiter = true;
		boolean expectLoremIpsum = true;
		SentenceGenerator forLoremIpsum = Random.sentence().forLoremIpsum();
		assertResult(forLoremIpsum, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);
	}

	@Test
	public void testForNumberOfWordsInt() {

		boolean expectEndDelimiter = true;
		boolean expectLoremIpsum = false;

		int expectWords = 0;
		SentenceGenerator generator = Random.sentence().forNumberOfWords(-1);
		assertResult(generator, expectWords, expectWords, expectEndDelimiter, expectLoremIpsum);

		expectWords = 0;
		generator = Random.sentence().forNumberOfWords(0);
		assertResult(generator, expectWords, expectWords, expectEndDelimiter, expectLoremIpsum);

		expectWords = 10;
		generator = Random.sentence().forNumberOfWords(10);
		assertResult(generator, expectWords, expectWords, expectEndDelimiter, expectLoremIpsum);
	}

	@Test
	public void testForNumberOfWordsIntInt() {
		boolean expectEndDelimiter = true;
		boolean expectLoremIpsum = false;

		int expectMinWords = 0;
		int expectMaxWords = 0;
		SentenceGenerator generator = Random.sentence().forNumberOfWords(-1, -1);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

		expectMinWords = 0;
		expectMaxWords = 0;
		generator = Random.sentence().forNumberOfWords(0, -1);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

		expectMinWords = 0;
		expectMaxWords = 0;
		generator = Random.sentence().forNumberOfWords(-1, 0);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

		expectMinWords = 0;
		expectMaxWords = 10;
		generator = Random.sentence().forNumberOfWords(10, 0);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

		expectMinWords = 10;
		expectMaxWords = 10;
		generator = Random.sentence().forNumberOfWords(10, 10);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

		expectMinWords = 10;
		expectMaxWords = 15;
		generator = Random.sentence().forNumberOfWords(10, 15);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

		expectMinWords = 15;
		expectMaxWords = 15;
		generator = Random.sentence().forNumberOfWords(15, 10);
		assertResult(generator, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);

	}

	@Test
	public void testForHasPunctuation() {
		int expectMinWords = 7;
		int expectMaxWords = 15;
		boolean expectEndDelimiter = false;
		boolean expectLoremIpsum = false;
		SentenceGenerator forLoremIpsum = Random.sentence().forNoEndPunctuation();
		assertResult(forLoremIpsum, expectMinWords, expectMaxWords, expectEndDelimiter, expectLoremIpsum);
	}

}
