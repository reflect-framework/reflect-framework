package nth.reflect.fw.infrastructure.random;

public class ValueGenerator<T> extends RandomGenerator<T> {

	private final T value;

	public ValueGenerator(T value) {
		this.value = value;
	}
	
	@Override
	public T generate() {
		return value;
	}

}
