package nth.introspect.filter2;


//TODO implement the java.util.function.Predicate when Eclipse java 8 complier issues are solved
public interface Predicate2<T> {

	/**
	 * Evaluates this predicate on the given argument.
	 *
	 * @param t
	 *            the input argument
	 * @return {@code true} if the input argument matches the predicate,
	 *         otherwise {@code false}
	 */
	boolean test(T t);

}
