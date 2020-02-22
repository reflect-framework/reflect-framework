package nth.reflect.fw.layer5provider.stringconverter.abstractconverter;

public interface ToStringConverter<T> {

	/**
	 * Converts the object provided into its string form. Format of the returned
	 * string is defined by the specific converter.
	 * 
	 * @return a string representation of the object passed in.
	 */
	public String toString(T value);
}
