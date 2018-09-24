package nth.reflect.fw.infrastructure.randomfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomListFactory<T> implements Factory<List<T>> {

	private final Factory<List<T>> randomListFactory;

	public RandomListFactory(int size, Factory<T> randomFactory) {
		randomListFactory=new Factory<List<T>>() {

			@Override
			public List<T> create() {
				return createList(randomFactory, size);
			}
		};
	}
	
	public RandomListFactory(int min, int max, Factory<T> randomFactory) {
		randomListFactory=new Factory<List<T>>() {

			@Override
			public List<T> create() {
				int size = ThreadLocalRandom.current().nextInt(min, max);
				return createList(randomFactory, size);
			}
		};
	}

	private List<T> createList(Factory<T> randomFactory, int size) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(randomFactory.create());
		}
		return list;
	}

	@Override
	public List<T> create() {
		return randomListFactory.create();
	}
}
