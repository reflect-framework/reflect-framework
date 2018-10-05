package nth.reflect.fw.infrastructure.random.generator.collection;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.resource.Repository;

/**
 * 
 * Generates a string from a given allValues of strings
 */
public class FromListGenerator<T> extends RandomGenerator<T> {

	private final Repository<T> repository;

public FromListGenerator(Repository<T> repository) {
	this.repository=repository;
}
	/**
	 * @param allValues
	 *            of items to generate from
	 */
	public FromListGenerator(List<T> allValues) {
		this(new Repository<T>() {

			@Override
			public List<T> getAll() {
				return allValues;
			}
		});
	}


	public FromListGenerator<T> forValues(List<T> allValues) {
		return new FromListGenerator<T>(allValues);
	}

	@Override
	public T generate() {
		List<T> allValues = repository.getAll();
		if (allValues.size() == 0) {
			throw new RuntimeException("No values to generate from.");
		} else {
			int randomIndex = ThreadLocalRandom.current().nextInt(allValues.size());
			return allValues.get(randomIndex);
		}
	}
}
