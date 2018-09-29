package nth.reflect.fw.infrastructure.random.generator.collection;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

@SuppressWarnings("rawtypes")
public class FromEnumGenerator<T extends Enum> extends RandomGenerator<T> {

	private T[] enumerationValues;

	public FromEnumGenerator(Class<? extends T> enumClass) {
		enumerationValues = enumClass.getEnumConstants();
	}

	@Override
	public T generate() {
		int randomIndex = Random.intGenerator().forMax(enumerationValues.length).generate();
		return enumerationValues[randomIndex];
	}

}
