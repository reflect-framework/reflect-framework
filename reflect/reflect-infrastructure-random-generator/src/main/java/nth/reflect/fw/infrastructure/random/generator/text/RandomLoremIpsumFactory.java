package nth.reflect.fw.infrastructure.random.generator.text;

import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * 
 * @deprecated extend StringGenerator
 */
public class RandomLoremIpsumFactory extends RandomGenerator<String> {
	public static final String LOREM_IPSUM = "lorem ipsum dolor sit amet consetetur sadipscing elitr sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat sed diam voluptua at vero eos et accusam et justo duo dolores et ea rebum stet clita kasd gubergren no sea takimata sanctus est lorem ipsum dolor sit amet";
	private String[] loremIpsumWords = LOREM_IPSUM.split("\\s");
	public static final String PARAGRAPH_SEPERATOR = "\n\n";

	private final RandomGenerator<String> randomLoremIpsumFactory;

	public RandomLoremIpsumFactory(int numberOfWords) {
		randomLoremIpsumFactory = new RandomGenerator<String>() {

			@Override
			public String generate() {
				return createWords(numberOfWords);
			}
		};
	}

	public RandomLoremIpsumFactory(int minNumberOfWords, int maxNumberOfWords) {
		randomLoremIpsumFactory = new RandomGenerator<String>() {

			@Override
			public String generate() {
				return createWords(minNumberOfWords, maxNumberOfWords);
			}
		};
	}

	public RandomLoremIpsumFactory(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence,
			int minNumberOfSentences, int maxNumberOfSentences) {
		randomLoremIpsumFactory = new RandomGenerator<String>() {

			@Override
			public String generate() {
				return createSentences(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences,
						maxNumberOfSentences);
			}
		};
	}

	public RandomLoremIpsumFactory(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence,
			int minNumberOfSentences, int maxNumberOfSentences, int minNumberOfParagraphs, int maxNumberOfParagraphs) {
		randomLoremIpsumFactory = new RandomGenerator<String>() {

			@Override
			public String generate() {
				return createParagraphs(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences,
						maxNumberOfSentences, minNumberOfParagraphs, maxNumberOfParagraphs);
			}
		};
	}

	private String createWords(int min, int max) {
		int numberOfWords = ThreadLocalRandom.current().nextInt(min, max);
		return createWords(numberOfWords);
	}

	private String createWords(int numberOfWords) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numberOfWords; i++) {
			String word = getRandomWord();
			if (result.length() == 0) {
			//	word = StringUtils.capitalize(word);
				word="word";//FIXME
			} else {
				result.append(" ");
			}
			result.append(word);
		}
		return result.toString();
	}

	private String createSentence(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence) {
		return createWords(minNumberOfWordsInSentence, maxNumberOfWordsInSentence) + ".";
	}

	private String createSentences(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence,
			int minNumberOfSentences, int maxNumberOfSentences) {
		int numberOfSentences = 0;
		if (minNumberOfSentences == maxNumberOfSentences) {
			numberOfSentences = maxNumberOfSentences;
		} else {
			numberOfSentences = ThreadLocalRandom.current().nextInt(minNumberOfSentences, maxNumberOfSentences);
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numberOfSentences; i++) {
			if (result.length() > 0) {
				result.append(" ");
			}
			String sentence = createSentence(minNumberOfWordsInSentence, maxNumberOfWordsInSentence);
			result.append(sentence);
		}
		return result.toString();
	}

	private String createParagraphs(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence,
			int minNumberOfSentences, int maxNumberOfSentences, int minNumberOfParagraphs, int maxNumberOfParagraphs) {
		int numberOfParagraps = 0;
		if (minNumberOfParagraphs == maxNumberOfParagraphs) {
			numberOfParagraps = maxNumberOfParagraphs;
		} else {
			numberOfParagraps = ThreadLocalRandom.current().nextInt(minNumberOfParagraphs, maxNumberOfParagraphs);
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < numberOfParagraps; i++) {
			if (result.length() > 0) {
				result.append(PARAGRAPH_SEPERATOR);
			}
			String sentences = createSentences(minNumberOfWordsInSentence, maxNumberOfWordsInSentence,
					minNumberOfSentences, maxNumberOfSentences);
			result.append(sentences);
		}
		return result.toString();
	}

	private String getRandomWord() {
		int randomWordIndex = ThreadLocalRandom.current().nextInt(0, 49);
		return loremIpsumWords[randomWordIndex];
	}

	@Override
	public String generate() {
		return randomLoremIpsumFactory.generate();
	}
}
