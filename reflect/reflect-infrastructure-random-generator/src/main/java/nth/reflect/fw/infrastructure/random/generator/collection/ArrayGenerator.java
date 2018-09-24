package nth.reflect.fw.infrastructure.random.generator.collection;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class ArrayGenerator<T> implements RandomGenerator<T[]> {

	private final Class<T> typeOfT;
	private final int min;
	private final int max;
	private final RandomGenerator<T> randomGenerator;

	public ArrayGenerator(Class<T> typeOfT, RandomGenerator<T> randomGenerator) {
		this(typeOfT, 50, randomGenerator);
	}

	public ArrayGenerator(Class<T> typeOfT, int size, RandomGenerator<T> randomGenerator) {
		this(typeOfT, size, size, randomGenerator);
	}

	public ArrayGenerator(Class<T> typeOfT, int min, int max, RandomGenerator<T> randomGenerator) {
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
