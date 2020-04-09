package nth.reflect.util.random.generator.collection;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;

@SuppressWarnings("rawtypes")
public class FromEnumGenerator<T extends Enum> extends RandomGenerator<T> {

	private T[] enumerationValues;

	public FromEnumGenerator(Class<? extends T> enumClass) {
		enumerationValues = enumClass.getEnumConstants();
	}

	@Override
	public T generate() {
		int randomIndex = Random.integer().forMax(enumerationValues.length).generate();
		return enumerationValues[randomIndex];
	}

}
