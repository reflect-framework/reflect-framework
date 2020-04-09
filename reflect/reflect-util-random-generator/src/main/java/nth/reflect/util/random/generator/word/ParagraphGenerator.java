package nth.reflect.util.random.generator.word;

import nth.reflect.util.random.IntRange;
import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;

public class ParagraphGenerator extends RandomGenerator<String> {

	public static final int MIN_SENTENCES_DEFAULT = 5;
	public static final int MAX_SENTENCES_DEFAULT = 25;
	public static final String SENTENCE_SEPERATOR_DEFAULT = " ";
	private final SentenceGenerator sentenceGenerator;
	private final IntRange numberOfSentences;
	private final String sentenceSeperator;

	public ParagraphGenerator() {
		this(Random.sentence(), MIN_SENTENCES_DEFAULT, MAX_SENTENCES_DEFAULT, SENTENCE_SEPERATOR_DEFAULT);
	}
	
	public ParagraphGenerator(SentenceGenerator sentenceGenerator, int minSentences, int maxSentences, String sentenceSeperator) {
		this.sentenceGenerator = sentenceGenerator;
		this.sentenceSeperator = sentenceSeperator;
		this.numberOfSentences=new IntRange(minSentences, maxSentences);
	}
	
	public ParagraphGenerator forSentenceGenerator(SentenceGenerator sentenceGenerator) {
		return new ParagraphGenerator(sentenceGenerator, numberOfSentences.getMin(), numberOfSentences.getMax(), sentenceSeperator);
	}
	
	public ParagraphGenerator forNumberOfSentences(int numberOfSentences) {
		return new ParagraphGenerator(sentenceGenerator, numberOfSentences, numberOfSentences, sentenceSeperator);
	}

	public ParagraphGenerator forNumberOfSentences(int minSentences,int maxSentences) {
		return new ParagraphGenerator(sentenceGenerator, maxSentences, maxSentences, sentenceSeperator);
	}

	public ParagraphGenerator forSentenceSeperator(String sentenceSeperator) {
		return new ParagraphGenerator(sentenceGenerator, numberOfSentences.getMin(), numberOfSentences.getMax(), sentenceSeperator);
	}

	@Override
	public String generate() {
		String paragraph = sentenceGenerator.generateString(numberOfSentences.getMin(), numberOfSentences.getMax(), sentenceSeperator);
		return paragraph;
	}

}
