package nth.reflect.fw.infrastructure.random.generator.collection;

import java.lang.reflect.Array;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * Generates a {@link Array} of generated results.
 * @author nilsth
 *
 * @param <T> the result type of the generator
 */
public class ArrayGenerator<T> extends RandomGenerator<T[]> {

	private final IntRange range;
	private final Class<T> typeOfT;
	private final RandomGenerator<T> randomGenerator;

	public ArrayGenerator(Class<T> typeOfT,  RandomGenerator<T> randomGenerator, int size) {
		this(typeOfT, randomGenerator, size, size);
	}

	public ArrayGenerator(Class<T> typeOfT, RandomGenerator<T> randomGenerator, int min, int max) {
		this.typeOfT = typeOfT;
		this.range=new IntRange(min, max);
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
		int size = range.getRandomInt();
		T[] arrayToFill = (T[]) Array.newInstance(typeOfT, size);
		for (int i = 0; i < size; i++) {
			arrayToFill[i] = randomGenerator.generate();
		}
		return arrayToFill;
	}


}
