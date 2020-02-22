package nth.reflect.fw.layer5provider.stringconverter.abstractconverter;

public interface FromStringConverter<T> {

	/**
	 * Converts the string provided into an object defined by the specific
	 * converter. Format of the string and type of the resulting object is defined
	 * by the specific converter.
	 * 
	 * @return an object representation of the string passed in.
	 */
	public T fromStringConverter(String value);
}
