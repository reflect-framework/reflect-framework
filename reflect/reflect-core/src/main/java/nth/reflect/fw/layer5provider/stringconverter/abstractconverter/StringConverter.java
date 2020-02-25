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
public abstract class StringConverter {

	protected final DependencyInjectionContainer container;
	protected final Optional<String> formatPattern;

	public StringConverter(DependencyInjectionContainer container, Optional<String> formatPattern) {
		this.container = container;
		this.formatPattern = formatPattern;
	}
	
	/**
	 * Converts the object provided into its string form. Format of the returned
	 * string is defined by the specific converter.
	 * 
	 * @return a string representation of the object passed in.
	 */
	public abstract String toString(Object value);
	
	/**
	 * Converts the string provided into an object defined by the specific
	 * converter. Format of the string and type of the resulting object is defined
	 * by the specific converter.
	 * 
	 * @return an object representation of the string passed in.
	 */
	public abstract  Object fromStringConverter(String value);

}
