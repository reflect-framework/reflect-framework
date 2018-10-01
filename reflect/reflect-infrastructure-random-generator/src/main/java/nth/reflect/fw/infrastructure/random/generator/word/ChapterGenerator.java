package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;

public class ChapterGenerator extends StringGenerator {

	public static final int MIN_PARAGRAPHS_DEFAULT = 5;
	public static final int MAX_PARAGRAPHS_DEFAULT = 25;
	public static final String PARAGRAPH_SEPERATOR_DEFAULT = "\n\n";
	private final ParagraphGenerator paragraphGenerator;
	private final IntRange numberOfParagraphs;
	private final String paragraphSeperator;

	public ChapterGenerator() {
		this(Random.paragraphGenerator(), MIN_PARAGRAPHS_DEFAULT, MAX_PARAGRAPHS_DEFAULT, PARAGRAPH_SEPERATOR_DEFAULT);
	}

	public ChapterGenerator(ParagraphGenerator paragraphGenerator, int minParagraphs, int maxParagraphs,
			String paragraphSeperator) {
		this.paragraphGenerator = paragraphGenerator;
		this.paragraphSeperator = paragraphSeperator;
		this.numberOfParagraphs = new IntRange(minParagraphs, maxParagraphs);
	}

	public ChapterGenerator forParagraphGenerator(ParagraphGenerator paragraphGenerator) {
		return new ChapterGenerator(paragraphGenerator, numberOfParagraphs.getMin(), numberOfParagraphs.getMax(),
				paragraphSeperator);
	}

	public ChapterGenerator forNumberOfParagraphs(int numberOfParagraphs) {
		return new ChapterGenerator(paragraphGenerator, numberOfParagraphs, numberOfParagraphs, paragraphSeperator);
	}

	public ChapterGenerator forNumberOfParagraphs(int minParagraphs, int maxParagraphs) {
		return new ChapterGenerator(paragraphGenerator, maxParagraphs, maxParagraphs, paragraphSeperator);
	}

	public ChapterGenerator forParagraphsSeperator(String paragraphsSeperator) {
		return new ChapterGenerator(paragraphGenerator, numberOfParagraphs.getMin(), numberOfParagraphs.getMax(),
				paragraphsSeperator);
	}

	@Override
	public String generate() {
		String chapter = paragraphGenerator.generateString(numberOfParagraphs.getMin(), numberOfParagraphs.getMax(),
				paragraphSeperator);
		return chapter;
	}

}
