package nth.reflect.fw.infrastructure.random.generator.word;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.util.StringUtil;

public class ChapterGeneratorTest {

	@Test
	public void testForNoParameters() {
		List<String> chapters = Random.chapter().generateList(100);
		for (String chapter : chapters) {
			int nrOfParagraphs = StringUtil.countMatches(chapter, ChapterGenerator.PARAGRAPH_SEPERATOR_DEFAULT) + 1;
			assertThat(nrOfParagraphs).isGreaterThanOrEqualTo(ChapterGenerator.MIN_PARAGRAPHS_DEFAULT);
			assertThat(nrOfParagraphs).isLessThanOrEqualTo(ChapterGenerator.MAX_PARAGRAPHS_DEFAULT);
		}

	}

	@Test
	public void testForParagraphGenerator() {
		SentenceGenerator sentenceGenerator = Random.sentence().forNumberOfWords(1);
		ParagraphGenerator paragraphGenerator = Random.paragraph().forSentenceGenerator(sentenceGenerator)
				.forNumberOfSentences(1).forSentenceSeperator("");
		List<String> paragraphs = Random.chapter().forParagraphGenerator(paragraphGenerator).generateList(100);
		for (String paragraph : paragraphs) {
			int numberOfSpaces = StringUtil.countMatches(paragraph, " ");
			assertThat(numberOfSpaces).isEqualTo(0);
		}
	}

	@Test
	public void testForNumberOfParagraphsInt() {
		int expectedParagraphs = 10;
		List<String> chapters = Random.chapter().forNumberOfParagraphs(expectedParagraphs).generateList(100);
		for (String chapter : chapters) {
			int nrOfParagraps = StringUtil.countMatches(chapter, ChapterGenerator.PARAGRAPH_SEPERATOR_DEFAULT) + 1;
			assertThat(nrOfParagraps).isEqualTo(expectedParagraphs);
		}
	}

	@Test
	public void testForNumberOfParagraphsIntInt() {
		int minParagraphs = 10;
		int maxParagraphs = 100;
		List<String> chapters = Random.chapter().forNumberOfParagraphs(minParagraphs, maxParagraphs).generateList(100);
		for (String chapter : chapters) {
			int nrOfParagraphs = StringUtil.countMatches(chapter, ChapterGenerator.PARAGRAPH_SEPERATOR_DEFAULT) + 1;
			assertThat(nrOfParagraphs).isGreaterThanOrEqualTo(minParagraphs);
			assertThat(nrOfParagraphs).isLessThanOrEqualTo(maxParagraphs);
		}
	}

	@Test
	public void testForParagraphsSeperator() {
		String paragraphSeperator = "-";
		int expectedNumberOfParagraphs = 10;
		List<String> chapters = Random.chapter().forParagraphsSeperator(paragraphSeperator)
				.forNumberOfParagraphs(expectedNumberOfParagraphs).generateList(100);
		for (String chapter : chapters) {
			int actualNumberOfParagraphs = StringUtil.countMatches(chapter, paragraphSeperator) + 1;
			assertThat(actualNumberOfParagraphs).isEqualTo(expectedNumberOfParagraphs);
		}
	}

}
