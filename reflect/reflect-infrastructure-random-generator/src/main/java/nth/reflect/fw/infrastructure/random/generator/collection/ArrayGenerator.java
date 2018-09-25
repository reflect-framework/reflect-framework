package nth.reflect.fw.infrastructure.random.generator.collection;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * Generates a {@link Array} of generated results.
 * @author nilsth
 *
 * @param <T> the result type of the generator
 */
public class ArrayGenerator<T> extends RandomGenerator<T[]> {

	private final Class<T> typeOfT;
	private final int min;
	private final int max;
	private final RandomGenerator<T> randomGenerator;

	public ArrayGenerator(Class<T> typeOfT,  RandomGenerator<T> randomGenerator, int size) {
		this(typeOfT, randomGenerator, size, size);
	}

	public ArrayGenerator(Class<T> typeOfT, RandomGenerator<T> randomGenerator, int min, int max) {
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.typeOfT = typeOfT;
		this.min = min;
		this.max = max;
		this.randomGenerator = randomGenerator;
	}

	public ArrayGenerator<T> forSize(int size) {
		return new ArrayGenerator<T>(typeOfT, randomGenerator, size);
	}

	public ArrayGenerator<T> forRange(int min, int max) {
		return new ArrayGenerator<T>(typeOfT, randomGenerator, min,max);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] generate() {
		int size = getSize();
		T[] arrayToFill = (T[]) Array.newInstance(typeOfT, size);
		for (int i = 0; i < size; i++) {
			arrayToFill[i] = randomGenerator.generate();
		}
		return arrayToFill;
	}

	private int getSize() {
		if (min == max) {
			return max;
		} else {
			int generatedSize = ThreadLocalRandom.current().nextInt(min, max);
			return generatedSize;
		}
	}

}
