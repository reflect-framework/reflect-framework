package nth.reflect.fw.infrastructure.random.generator.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * Generates a {@link ArrayList} of generated results.
 * @author nilsth
 *
 * @param <T> the result type of the generator
 */
public class ListGenerator<T> extends RandomGenerator<List<T>> {

	private final int min;
	private final int max;
	private final RandomGenerator<T> randomGenerator;

	public ListGenerator( RandomGenerator<T> randomGenerator, int size) {
		this( randomGenerator,size, size);
	}

	public ListGenerator(RandomGenerator<T> randomGenerator, int min, int max) {
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.min = min;
		this.max = max;
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
		int size = getSize();
		List<T> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomGenerator.generate());
		}
		return list;
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
