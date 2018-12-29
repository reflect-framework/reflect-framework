package nth.reflect.fw.infrastructure.random.generator.word;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.util.StringUtil;
import nth.reflect.fw.infrastructure.random.generator.word.ChapterGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.ParagraphGenerator;
import nth.reflect.fw.infrastructure.random.generator.word.SentenceGenerator;

public class ChapterGeneratorTest {

	@Test
	public void testForNoParameters() {
		List<String> chapters = Random.chapter().generateList(100);
		for (String chapter : chapters) {
			int nrOfParagraphs = StringUtil.countMatches(chapter, ChapterGenerator.PARAGRAPH_SEPERATOR_DEFAULT) + 1;
			assertThat(nrOfParagraphs, greaterThanOrEqualTo(ChapterGenerator.MIN_PARAGRAPHS_DEFAULT));
			assertThat(nrOfParagraphs, lessThanOrEqualTo(ChapterGenerator.MAX_PARAGRAPHS_DEFAULT));
		}

	}

	@Test
	public void testForParagraphGenerator() {
		SentenceGenerator sentenceGenerator = Random.sentence().forNumberOfWords(1);
		ParagraphGenerator paragraphGenerator = Random.paragraph().forSentenceGenerator(sentenceGenerator)
				.forNumberOfSentences(1).forSentenceSeperator("");
		List<String> paragraphs = Random.chapter().forParagraphGenerator(paragraphGenerator)
				.generateList(100);
		for (String paragraph : paragraphs) {
			assertEquals(0, StringUtil.countMatches(paragraph, " "));
		}
	}

	@Test
	public void testForNumberOfParagraphsInt() {
		int expectedParagraphs = 10;
		List<String> chapters = Random.chapter().forNumberOfParagraphs(expectedParagraphs)
				.generateList(100);
		for (String chapter : chapters) {
			int nrOfParagraps = StringUtil.countMatches(chapter,ChapterGenerator.PARAGRAPH_SEPERATOR_DEFAULT)+1;
			assertThat(nrOfParagraps, equalTo(expectedParagraphs));
		}
	}

	@Test
	public void testForNumberOfParagraphsIntInt() {
		int minParagraphs = 10;
		int maxParagraphs = 100;
		List<String> chapters = Random.chapter().forNumberOfParagraphs(minParagraphs, maxParagraphs)
				.generateList(100);
		for (String chapter : chapters) {
			int nrOfParagraphs = StringUtil.countMatches(chapter,ChapterGenerator.PARAGRAPH_SEPERATOR_DEFAULT)+1;
			assertThat(nrOfParagraphs, greaterThanOrEqualTo(minParagraphs));
			assertThat(nrOfParagraphs, lessThanOrEqualTo(maxParagraphs));
		}
	}

	@Test
	public void testForParagraphsSeperator() {
		String paragraphSeperator = "-";
		int expectedParagraphs = 10;
		List<String> chapters = Random.chapter().forParagraphsSeperator(paragraphSeperator)
				.forNumberOfParagraphs(expectedParagraphs).generateList(100);
		for (String chapter : chapters) {
			assertEquals(expectedParagraphs,StringUtil.countMatches(chapter, paragraphSeperator)+1);
		}	}

}
