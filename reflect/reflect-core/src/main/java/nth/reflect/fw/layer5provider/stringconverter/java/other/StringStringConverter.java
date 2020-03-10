package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

/**
 * Looks like a silly {@link StringConverter}, but it is needed so that
 * {@link String}s are supported. Note that formatting is not supported, since
 * there are no parameters
 * 
 * @author nilsth
 *
 */
public class StringStringConverter extends StringConverter<String> {

	public StringStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
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
