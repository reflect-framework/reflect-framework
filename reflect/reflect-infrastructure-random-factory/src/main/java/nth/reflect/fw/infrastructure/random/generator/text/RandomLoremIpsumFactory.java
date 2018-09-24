/* Copyright (c) 2008 Sven Jacobs

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in
   all copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
   THE SOFTWARE. 
*/

package nth.reflect.fw.infrastructure.randomfactory;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;

/**
 * Simple lorem ipsum text generator.
 * 
 * <p>
 * Suitable for creating sample data for test cases and performance tests.
 * </p>
 *
 * @author Sven Jacobs
 * @version 1.0
 */
public class RandomLoremIpsumFactory implements Factory<String> {
	public static final String LOREM_IPSUM = "lorem ipsum dolor sit amet consetetur sadipscing elitr sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat sed diam voluptua at vero eos et accusam et justo duo dolores et ea rebum stet clita kasd gubergren no sea takimata sanctus est lorem ipsum dolor sit amet";
	private String[] loremIpsumWords = LOREM_IPSUM.split("\\s");
	public static final String PARAGRAPH_SEPERATOR = "\n\n";

	private final Factory<String> randomLoremIpsumFactory;

	public RandomLoremIpsumFactory(int numberOfWords) {
		randomLoremIpsumFactory = new Factory<String>() {

			@Override
			public String create() {
				return createWords(numberOfWords);
			}
		};
	}

	public RandomLoremIpsumFactory(int minNumberOfWords, int maxNumberOfWords) {
		randomLoremIpsumFactory = new Factory<String>() {

			@Override
			public String create() {
				return createWords(minNumberOfWords, maxNumberOfWords);
			}
		};
	}

	public RandomLoremIpsumFactory(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence,
			int minNumberOfSentences, int maxNumberOfSentences) {
		randomLoremIpsumFactory = new Factory<String>() {

			@Override
			public String create() {
				return createSentences(minNumberOfWordsInSentence, maxNumberOfWordsInSentence, minNumberOfSentences,
						maxNumberOfSentences);
			}
		};
	}

	public RandomLoremIpsumFactory(int minNumberOfWordsInSentence, int maxNumberOfWordsInSentence,
			int minNumberOfSentences, int maxNumberOfSentences, int minNumberOfParagraphs, int maxNumberOfParagraphs) {
		randomLoremIpsumFactory = new Factory<String>() {

			@Override
			public String create() {
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
				word = StringUtils.capitalize(word);
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
	public String create() {
		return randomLoremIpsumFactory.create();
	}
}
