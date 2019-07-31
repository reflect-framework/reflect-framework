package nth.reflect.fw.gui.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class UnmodifiableCollection<E> implements Collection<E>, Serializable {
	private static final long serialVersionUID = 1820017752578914078L;

	protected final Collection<? extends E> c;

	protected UnmodifiableCollection(Collection<? extends E> c) {
		if (c == null)
			throw new NullPointerException();
		this.c = c;
	}

	@Override
	public int size() {
		return c.size();
	}

	@Override
	public boolean isEmpty() {
		return c.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return c.contains(o);
	}

	@Override
	public Object[] toArray() {
		return c.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return c.toArray(a);
	}

	@Override
	public String toString() {
		return c.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private final Iterator<? extends E> i = c.iterator();

			@Override
			public boolean hasNext() {
				return i.hasNext();
			}

			@Override
			public E next() {
				return i.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void forEachRemaining(Consumer<? super E> action) {
				// Use backing collection version
				i.forEachRemaining(action);
			}
		};
	}

	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		return c.containsAll(coll);
	}

	@Override
	public boolean addAll(Collection<? extends E> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> coll) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	// Override default methods in Collection
	@Override
	public void forEach(Consumer<? super E> action) {
		c.forEach(action);
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spliterator<E> spliterator() {
		return (Spliterator<E>) c.spliterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<E> stream() {
		return (Stream<E>) c.stream();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stream<E> parallelStream() {
		return (Stream<E>) c.parallelStream();
	}
}
