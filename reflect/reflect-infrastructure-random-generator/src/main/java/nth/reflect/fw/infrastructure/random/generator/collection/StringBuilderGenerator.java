package nth.reflect.fw.infrastructure.random.generator.collection;

import java.util.concurrent.ThreadLocalRandom;

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
	private final int min;
	private final int max;
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
		if (generationTimesMin > generationTimesMax) {
			int temp = generationTimesMin;
			generationTimesMin = generationTimesMax;
			generationTimesMax = temp;
		}
		this.randomGenerator = randomGenerator;
		this.min = generationTimesMin;
		this.max = generationTimesMax;
		this.seperator = seperator;

	}

	@Override
	public String generate() {
		StringBuilder result = new StringBuilder();
		int size = getSize();
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

	private int getSize() {
		if (min == max) {
			return max;
		} else {
			int randomSize = ThreadLocalRandom.current().nextInt(min, max);
			return randomSize;
		}
	}
}