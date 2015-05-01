package nth.introspect.filter2;

import java.util.function.Predicate;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.property.PropertyInfo;

public abstract class Filter2<T> implements Predicate2<T> {

	protected static final int GREATER_OR_EQUAL = 0;

	public Filter2<T> and(final Filter2<T> filter2) {
		final Filter2<T> filter1 = this;
		return new Filter2<T>() {

			@Override
			public boolean test(T t) {
				boolean result1 = filter1.test(t);
				boolean result2 = filter2.test(t);
				return result1 && result2;
			}
		};
	}

	public Filter2<T> or(final Filter2<T> filter2) {
		final Filter2<T> filter1 = this;
		return new Filter2<T>() {

			@Override
			public boolean test(T t) {
				boolean result1 = filter1.test(t);
				boolean result2 = filter2.test(t);
				return result1 || result2;
			}
		};
	}

	public Filter2<T> andNot(final Filter2<T> filter2) {
		final Filter2<T> filter1 = this;
		return new Filter2<T>() {

			@Override
			public boolean test(T t) {
				boolean result1 = filter1.test(t);
				boolean result2 = filter2.test(t);
				return result1 && !result2;
			}
		};
	}

	public Filter2<T> orNot(final Filter2<T> filter2) {
		final Filter2<T> filter1 = this;
		return new Filter2<T>() {

			@Override
			public boolean test(T t) {
				boolean result1 = filter1.test(t);
				boolean result2 = filter2.test(t);
				return result1 || !result2;
			}
		};
	}


	public Filter2<Comparable> greatherThan(Comparable comparable1) {
		return new Filter2<Comparable>() {

			@Override
			public boolean test(Comparable comparable2) {
					return nullSafeCompare(comparable1, comparable2)>0;
			}
		};
	}
	
	public static Predicate<?> greatherOrEqualTo(Comparable comparable) {
		return new Predicate<Object>() {

			@Override
			public boolean test(Object object) {
				if (object instanceof Comparable<?>) {
					return 0>=comparable.compareTo(object);	
				} else {
					throw new IllegalArgumentException("Must compare to type Comparable");
				}
			}
		};
	}
	public static Predicate<?> lessThan(Comparable comparable) {
		return new Predicate<Object>() {

			@Override
			public boolean test(Object object) {
				if (object instanceof Comparable<?>) {
					return 0<comparable.compareTo(object);	
				} else {
					throw new IllegalArgumentException("Must compare to type Comparable");
				}
				
			}
		};
	}
	
	public static Predicate<?> lessOrEqualTo(Comparable comparable) {
		return new Predicate<Object>() {

			@Override
			public boolean test(Object object) {
				if (object instanceof Comparable<?>) {
					return 0<=comparable.compareTo(object);	
				} else {
					throw new IllegalArgumentException("Must compare to type Comparable");
				}
			}
		};
	}

	/**
	 * Compares 2 objects in a null safe way
	 * 
	 * @param o1
	 *            The first object to be compared
	 * @param o2
	 *            The second object to be compared
	 * @return A positive number if the first object is bigger than the second,
	 *         a negative if is lesser or zero if they are equal
	 */
	public static int nullSafeCompare(Object o1, Object o2) {
		return nullSafeCompare(DEFAULT_ARGUMENT_COMPARATOR, o1, o2);
	}

	static int nullSafeCompare(Comparator<Object> comparator, Object o1,
			Object o2) {
		if (o1 == null)
			return o2 == null ? 0 : 1;
		return o2 == null ? -1 : comparator.compare(o1, o2);
	}

	
	
}
