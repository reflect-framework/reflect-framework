package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.net.URL;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class UrlStringConverter extends StringConverter<URL> {

	public UrlStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	public String toString(URL value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	@Override
	public URL fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			URL url = new URL(value);
			return url;

		} catch (Exception e) {
			throw new StringConverterException(this, value, e);
		}
	}

}
