package nth.reflect.fw.infrastructure.randomfactory;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayFactory<T> implements Factory<T[]> {

	private final Factory<T[]> randomArrayFactory;

	public RandomArrayFactory(Class<T> typeOfT, int size, Factory<T> randomFactory) {
		randomArrayFactory = new Factory<T[]>() {

			@Override
			public T[] create() {
				return createArray(typeOfT, size, randomFactory);
			}
		};
	}


	@Override
	public String toString() {
		return "RandomArrayFactory [randomArrayFactory=" + randomArrayFactory + "]";
	}

	public RandomArrayFactory(Class<T> typeOfT, int min, int max, Factory<T> randomFactory) {
		randomArrayFactory = new Factory<T[]>() {

			@Override
			public T[] create() {
				int size = ThreadLocalRandom.current().nextInt(min, max);
				return createArray(typeOfT, size, randomFactory);
			}
		};
	}

	@SuppressWarnings("unchecked")
	private T[] createArray(Class<T> typeOfT, int size, Factory<T> randomFactory) {
		T[] arrayToFill = (T[]) Array.newInstance(typeOfT, size);
		for (int i = 0; i < size; i++) {
			arrayToFill[i] = randomFactory.create();
		}
		return arrayToFill;
	}



	@Override
	public T[] create() {
		return randomArrayFactory.create();
	}

}
