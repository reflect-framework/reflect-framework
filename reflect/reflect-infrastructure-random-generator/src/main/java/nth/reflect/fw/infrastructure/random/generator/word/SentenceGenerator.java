package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.number.BoolGenerator;
import nth.reflect.fw.infrastructure.random.generator.util.StringUtil;

public class SentenceGenerator extends RandomGenerator<String> {

	private static final int MAX_WORD_DEFAULT = 15;
	private static final int MIN_WORD_DEFAULT = 7;
	private final RandomGenerator<String> wordGenerator;
	private final IntRange numberOfWords;
	private final BoolGenerator hasDotGenerator;
	private final BoolGenerator hasQuestionMarkGenerator;
	private final boolean hasPunctuation;

	
	public SentenceGenerator() {
		this(new WordGenerator(),MIN_WORD_DEFAULT,MAX_WORD_DEFAULT,true);
	}
	
	public SentenceGenerator(RandomGenerator<String> wordGenerator, int minWords,int maxWords, boolean hasPunctuation) {
		this.wordGenerator = wordGenerator;
		this.numberOfWords=new IntRange(minWords, maxWords);
		this.hasPunctuation = hasPunctuation;
		hasDotGenerator=Random.bool().forProbability(90);
		hasQuestionMarkGenerator=Random.bool();
	}
	
	public SentenceGenerator forLoremIpsum() {
		return new SentenceGenerator(new LoremIpsumGenerator(), numberOfWords.getMin(), numberOfWords.getMax(), hasPunctuation);
	}
	
	public SentenceGenerator forNumberOfWords(int nrOfWords) {
		return new SentenceGenerator(wordGenerator, nrOfWords, nrOfWords, hasPunctuation);
	}
	
	public SentenceGenerator forNumberOfWords( int minWords,int maxWords) {
		return new SentenceGenerator(wordGenerator, minWords, maxWords, hasPunctuation);
	}

	public SentenceGenerator forNoEndPunctuation() {
		return new SentenceGenerator(wordGenerator, numberOfWords.getMin(), numberOfWords.getMax(), false);
	}
	
	
	@Override
	public String generate() {
		StringBuilder sentence = new StringBuilder();
		 appendWords(sentence);	
		if (hasPunctuation) {
			appendPunctuation(sentence);
		}
		return sentence.toString();
	}

	private void appendWords(StringBuilder sentence) {
		String words = wordGenerator.generateString(numberOfWords.getMin(), numberOfWords.getMax(), " ");
		 String wordstWithFirstLetterCaplitalized = StringUtil.capitalize(words);
		sentence.append(wordstWithFirstLetterCaplitalized);
	}

	private void appendPunctuation(StringBuilder sentence) {
		if (hasDotGenerator.generate()) {
			sentence.append(".");
		} else {
			if (hasQuestionMarkGenerator.generate()) {
				sentence.append("?");
			} else {
				sentence.append("!");
			}
		}
	}
	

}
