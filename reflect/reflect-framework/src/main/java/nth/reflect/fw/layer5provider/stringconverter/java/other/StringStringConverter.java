package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

/**
 * Looks like a silly {@link StringConverter}, but it is needed so that
 * {@link String}s are supported. Note that formatting is not supported, since
 * there are no parameters
 * 
 * @author nilsth
 *
 */
public class StringStringConverter extends StringConverter<String> {

	public StringStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	public String toString(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}

	@Override
	public String fromString(String value) {
		return value;
	}

}
