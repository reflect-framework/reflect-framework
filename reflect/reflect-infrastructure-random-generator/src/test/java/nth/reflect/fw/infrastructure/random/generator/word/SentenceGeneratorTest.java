package nth.reflect.fw.infrastructure.random.generator.word;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.word.SentenceGenerator;

public class SentenceGeneratorTest {
	private static final String END_DELIMITERS = ".!?";
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

	@SuppressWarnings("unchecked")
	private void assertResult(SentenceGenerator sentenceGenerator, int expectMinWords, int expectMaxWords,
			boolean expectEndDelimiter, boolean expectLoremIpsum) {
		List<String> sentences = sentenceGenerator.generateList(100);
		for (String sentence : sentences) {
			String lastChar = sentence.substring(sentence.length() - 1, sentence.length());
			if (expectEndDelimiter) {
				assertTrue("Sentence must end with delimeter: " + sentence, END_DELIMITERS.contains(lastChar));
				sentence = sentence.substring(0, sentence.length() - 1);
			} else {
				assertTrue("Sentence may not end with delimeter: " + sentence, !(END_DELIMITERS.contains(lastChar)));
			}
			if (sentence.length() > 0 || expectMinWords > 0) {
				String[] words = sentence.trim().split(REG_EXP_SPACES);
				assertThat(words.length, greaterThan(expectMinWords - 1));
				assertThat(words.length, lessThan(expectMaxWords + 1));
				assertThat(Arrays.asList(words), hasItems(not(isEmptyOrNullString())));
				assertTrue("Sentence must start with capital letter: " + sentence,
						Character.isUpperCase(sentence.charAt(0)));
				
				if (expectLoremIpsum) {
					assertLoremIpsumWords(words);
				}
			}

			
		}
	}

	private void assertLoremIpsumWords(String[] words) {
		for (String word : words) {
			String normilizedWord = word.toLowerCase().replace(".", "").replace("!", "").replace("?", "");
			assertTrue("Must be a Lorem Ipsum word: " + normilizedWord, loremIpsumWords.contains(normilizedWord));
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
	public void testForNumberOfWordsInt(){
		
		boolean expectEndDelimiter = true;
		boolean expectLoremIpsum = false;
		
		int expectWords = 0;
		SentenceGenerator generator = Random.sentence().forNumberOfWords( -1);
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

		expectMinWords = 10;
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
