package nth.reflect.fw.infrastructure.randomfactory;

public interface Factory<T> {
// rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	public T create();
}
