package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

/**
 * A stupid {@link StringConverter}, but it is needed so that Strings are
 * supported Note that formatting is not supported, since there are no
 * parameters
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
		return value;
	}

	@Override
	public String fromString(String value) {
		return value;
	}

}
