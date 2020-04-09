package nth.reflect.util.random.generator.collection;

import java.util.HashSet;
import java.util.Set;

import nth.reflect.util.random.IntRange;
import nth.reflect.util.random.RandomGenerator;

/**
 * <p>
 * Generates a {@link HashSet} of unique generated results.
 * </p>
 * <p>
 * Note that the number of generations may be less than requested because since
 * the generator may not generate enough unique results.
 * </p>
 * 
 * @author nilsth
 *
 * @param <T>
 *            the result type of the generator
 */
public class SetGenerator<T> extends RandomGenerator<Set<T>> {

	private final IntRange range;
	private final RandomGenerator<T> randomGenerator;

	public SetGenerator(RandomGenerator<T> randomGenerator, int size) {
		this(randomGenerator, size, size);
	}

	public SetGenerator(RandomGenerator<T> randomGenerator, int min, int max) {
		this.range=new IntRange(min, max);
		this.randomGenerator = randomGenerator;
	}

	public SetGenerator<T> forSize(int size) {
		return new SetGenerator<>(randomGenerator, size);
	}

	public SetGenerator<T> forRange(int min, int max) {
		return new SetGenerator<>(randomGenerator, min, max);
	}

	@Override
	public Set<T> generate() {
		int size = range.getRandomInt();
		// maxGenerationCount prevents a deadlock since the generator may not
		// generate enough unique results.
		int maxGenerationCount = size * 10;
		Set<T> result = new HashSet<>();
		int generationCount = 0;
		while (result.size() < size && generationCount++ < maxGenerationCount) {
			result.add(randomGenerator.generate());
		}
		return result;
	}


}
