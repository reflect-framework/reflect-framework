package nth.reflect.fw.infrastructure.randomfactory;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class RandomLoremIpsumFactoryTest {

	


	@Test
	public void testLoremIpsumWordsCreation() {
		int numberOfWords = 5;
		String string = new RandomLoremIpsumFactory(numberOfWords).create();
		assertTrue(firstCharachterIsUpperCase(string));
		assertTrue(StringUtils.countMatches(string, " ") == 4);
		assertTrue(StringUtils.countMatches(string, ".") == 0);
	}

	@Test
	public void testLoremIpsumWordsMinMax() {
		int minNumberOfWords = 5;
		int maxNumberOfWords = 10;
		RandomLoremIpsumFactory randomLoremIpsumFactory = new RandomLoremIpsumFactory(minNumberOfWords,
				maxNumberOfWords);
		for (int i = 0; i < 20; i++) {
			String string = randomLoremIpsumFactory.create();
			assertTrue(firstCharachterIsUpperCase(string));
			assertTrue(StringUtils.countMatches(string, " ") + 1 >= minNumberOfWords);
			assertTrue(StringUtils.countMatches(string, " ") + 1 <= maxNumberOfWords);
			assertTrue(StringUtils.countMatches(string, ".") == 0);
		}
	}

	@Test
	public void testLoremIpsumSentences() {
		int minNumberOfWordsInSentence=10;
		int maxNumberOfWordsInSentence=20;
		int minNumberOfSentences=5;
		int maxNumberOfSentences=10;
		RandomLoremIpsumFactory randomLoremIpsumFactory = new RandomLoremIpsumFactory(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences, maxNumberOfSentences);
		for (int i = 0; i < 20; i++) {
			String string = randomLoremIpsumFactory.create();
			assertTrue(firstCharachterIsUpperCase(string));
			assertTrue(StringUtils.countMatches(string, " ") + 1 >= minNumberOfWordsInSentence);
			assertTrue(StringUtils.countMatches(string, ".") >=minNumberOfSentences);
			assertTrue(StringUtils.countMatches(string, ".") <=maxNumberOfSentences);
		}
	}

	@Test
	public void testLoremIpsum10Sentences() {
		int minNumberOfWordsInSentence=10;
		int maxNumberOfWordsInSentence=20;
		int minNumberOfSentences=10;
		int maxNumberOfSentences=10;
		RandomLoremIpsumFactory randomLoremIpsumFactory = new RandomLoremIpsumFactory(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences, maxNumberOfSentences);
		for (int i = 0; i < 20; i++) {
			String string = randomLoremIpsumFactory.create();
			assertTrue(firstCharachterIsUpperCase(string));
			assertTrue(StringUtils.countMatches(string, " ") + 1 >= minNumberOfWordsInSentence);
			assertTrue(StringUtils.countMatches(string, ".") >=minNumberOfSentences);
			assertTrue(StringUtils.countMatches(string, ".") <=maxNumberOfSentences);
		}
	}

	@Test
	public void testLoremIpsumParagraphs() {
		int minNumberOfWordsInSentence=10;
		int maxNumberOfWordsInSentence=20;
		int minNumberOfSentences=5;
		int maxNumberOfSentences=10;
		int minNumberOfParagraphs=5;
		int maxNumberOfParagraphs=10;
		RandomLoremIpsumFactory randomLoremIpsumFactory = new RandomLoremIpsumFactory(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences, maxNumberOfSentences,minNumberOfParagraphs, maxNumberOfParagraphs);
		for (int i = 0; i < 20; i++) {
			String string = randomLoremIpsumFactory.create();
			assertTrue(firstCharachterIsUpperCase(string));
			assertTrue(StringUtils.countMatches(string, " ") + 1 >= minNumberOfWordsInSentence);
			assertTrue(StringUtils.countMatches(string, ".") >=minNumberOfSentences);
			assertTrue(StringUtils.countMatches(string, RandomLoremIpsumFactory.PARAGRAPH_SEPERATOR)+1 >=minNumberOfParagraphs);
			assertTrue(StringUtils.countMatches(string, RandomLoremIpsumFactory.PARAGRAPH_SEPERATOR)+1 <=maxNumberOfParagraphs);
		}
	}
	
	@Test
	public void testLoremIpsum10Paragraphs() {
		int minNumberOfWordsInSentence=10;
		int maxNumberOfWordsInSentence=20;
		int minNumberOfSentences=5;
		int maxNumberOfSentences=15;
		int minNumberOfParagraphs=3;
		int maxNumberOfParagraphs=3;
		RandomLoremIpsumFactory randomLoremIpsumFactory = new RandomLoremIpsumFactory(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences, maxNumberOfSentences,minNumberOfParagraphs, maxNumberOfParagraphs);
		for (int i = 0; i < 3; i++) {
			String string = randomLoremIpsumFactory.create();
			assertTrue(firstCharachterIsUpperCase(string));
			assertTrue(StringUtils.countMatches(string, " ") + 1 >= minNumberOfWordsInSentence);
			assertTrue(StringUtils.countMatches(string, ".") >=minNumberOfSentences);
			assertTrue(StringUtils.countMatches(string, RandomLoremIpsumFactory.PARAGRAPH_SEPERATOR)+1 ==minNumberOfParagraphs);
			assertTrue(StringUtils.countMatches(string, RandomLoremIpsumFactory.PARAGRAPH_SEPERATOR)+1 ==maxNumberOfParagraphs);
		}
	}

	
	private boolean firstCharachterIsUpperCase(String string) {
		return Character.isUpperCase(string.charAt(0));
	}

}
