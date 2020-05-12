package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.net.URI;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class UriStringConverter implements StringConverter<URI> {

	@Override
	public String toString(URI value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	@Override
	public URI fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			URI uri = new URI(value);
			return uri;

		} catch (Exception e) {
			throw new StringConverterException(this, value);
		}
	}

}
