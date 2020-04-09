package nth.reflect.util.random.generator.collection;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.util.random.IntRange;
import nth.reflect.util.random.RandomGenerator;

/**
 * Generates a {@link ArrayList} of generated results.
 * @author nilsth
 *
 * @param <T> the result type of the generator
 */
public class ListGenerator<T> extends RandomGenerator<List<T>> {

	private final IntRange range;
	private final RandomGenerator<T> randomGenerator;

	public ListGenerator( RandomGenerator<T> randomGenerator, int size) {
		this( randomGenerator,size, size);
	}

	public ListGenerator(RandomGenerator<T> randomGenerator, int min, int max) {
		this.range=new IntRange(min, max);
		this.randomGenerator = randomGenerator;
	}

	public ListGenerator<T> forSize(int size) {
		return new ListGenerator<>( randomGenerator, size);
	}

	public ListGenerator<T> forRange(int min, int max) {
		return new ListGenerator<>( randomGenerator, min, max);
	}

	@Override
	public List<T> generate() {
		int size = range.getRandomInt();
		List<T> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomGenerator.generate());
		}
		return list;
	}


}
