package nth.reflect.fw.layer5provider;

import java.util.List;

import nth.reflect.fw.ReflectFramework;

/**
 * Several {@link Provider}s use several
 * <a href="https://en.wikipedia.org/wiki/Delegation_pattern">delegation</a>
 * objects of given type T to do the work. These
 * <a href="https://en.wikipedia.org/wiki/Delegation_pattern">delegation</a>
 * objects are given to the {@link Provider}'s constructor using a
 * {@link ProviderFactory} when the {@link ReflectFramework} is initialized. The
 * {@link ProviderFactory} can be replaced or overridden so that you can
 * customize/ plug-in the
 * <a href="https://en.wikipedia.org/wiki/Delegation_pattern">delegation</a>
 * objects that you need.
 * 
 * @author nilsth
 *
 * @param <T>
 */
public interface ProviderFactory<T> {

	public List<T> getAll();
}
