package nth.reflect.infra.excel.repository.sheetfilter;


//Java 7 has issues with predicate. remove this interface when going to Java 7
public interface Predicate<T> {
	public boolean test(T object);
}
