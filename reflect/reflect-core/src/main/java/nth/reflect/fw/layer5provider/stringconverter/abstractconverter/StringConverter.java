package nth.reflect.fw.layer5provider.stringconverter.abstractconverter;

import java.util.Optional;

import nth.reflect.fw.container.DependencyInjectionContainer;

/**
 * A {@link StringConverter} converts type T to a {@link String}. A
 * {@link StringConverter} can implement a {@link FromStringConverter} to
 * convert a {@link String} to a type T.
 * 
 * @author nilsth
 *
 * @param <T> The type that the {@link StringConverter} converts from or to.
 */
public abstract class StringConverter<T> implements ToStringConverter<T> {

	protected final DependencyInjectionContainer container;
	protected final Optional<String> formatPattern;

	public StringConverter(DependencyInjectionContainer container, Optional<String> formatPattern) {
		this.container = container;
		this.formatPattern = formatPattern;
	}

}
