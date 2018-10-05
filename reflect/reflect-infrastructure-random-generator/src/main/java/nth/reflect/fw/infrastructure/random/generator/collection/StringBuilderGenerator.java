package nth.reflect.fw.infrastructure.random.generator.collection;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * Generates a {@link String} of generated results.
 * 
 * @author nilsth
 *
 * @param <T>
 *            the result type of the generator
 */
public class StringBuilderGenerator<T> extends RandomGenerator<String> {
	public static final String DEFAULT_SEPERATOR = ", ";
	private final IntRange generationTimesRange;
	private final RandomGenerator<T> randomGenerator;
	private final String seperator;

	public StringBuilderGenerator(RandomGenerator<T> randomGenerator, int generationTimes) {
		this(randomGenerator, generationTimes, generationTimes, DEFAULT_SEPERATOR);
	}

	public StringBuilderGenerator(RandomGenerator<T> randomGenerator, int generationTimes, String seperator) {
		this(randomGenerator, generationTimes, generationTimes, seperator);
	}

	public StringBuilderGenerator(RandomGenerator<T> randomGenerator, int generationTimesMin, int generationTimesMax) {
		this(randomGenerator, generationTimesMin, generationTimesMax, DEFAULT_SEPERATOR);
	}

	public StringBuilderGenerator(RandomGenerator<T> randomGenerator, int generationTimesMin, int generationTimesMax,
			String seperator) {
		this.randomGenerator = randomGenerator;
		this.generationTimesRange=new IntRange(generationTimesMin, generationTimesMax);
		this.seperator = seperator;

	}

	@Override
	public String generate() {
		StringBuilder result = new StringBuilder();
		int size = generationTimesRange.getRandomInt();
		for (int i = 0; i < size; i++) {
			T generatedResult = randomGenerator.generate();
			if (generatedResult != null) {
				if (result.length() > 0 && seperator != null) {
					result.append(seperator);
				}
				result.append(generatedResult);
			}
		}
		return result.toString();
	}

}