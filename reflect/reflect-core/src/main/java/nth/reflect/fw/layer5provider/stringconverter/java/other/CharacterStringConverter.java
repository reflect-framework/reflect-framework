package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

/**
 * A stupid {@link StringConverter}, but it is needed so that Strings are
 * supported Note that formatting is not supported, since there are no
 * parameters
 * 
 * @author nilsth
 *
 */
public class CharacterStringConverter extends StringConverter<Character> {

	public CharacterStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	public String toString(Character value) {
		if (value == null) {
			return "";
		}

		return value.toString();
	}

	@Override
	public Character fromString(String value) {
		if (value == null) {
			return null;
		}

		if (value.length() != 1) {
			throw new StringConverterException(this, value);
		}

		return Character.valueOf(value.charAt(0));
	}

}
