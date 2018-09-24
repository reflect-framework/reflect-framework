package nth.reflect.fw.infrastructure.random.generator.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class ListGenerator<T> implements RandomGenerator<List<T>> {

	private final int min;
	private final int max;
	private RandomGenerator<T> randomGenerator;

	public ListGenerator(RandomGenerator<T> randomGenerator) {
		this(50, randomGenerator);
	}

	public ListGenerator(int size, RandomGenerator<T> randomGenerator) {
		this(size, size, randomGenerator);
	}

	public ListGenerator(int min, int max, RandomGenerator<T> randomGenerator) {
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		this.min = min;
		this.max = max;
		this.randomGenerator = randomGenerator;
	}

	public ListGenerator<T> forMin(int min) {
		return new ListGenerator<>(min, max, randomGenerator);
	}

	public ListGenerator<T> forMax(int max) {
		return new ListGenerator<>(min, max, randomGenerator);
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
