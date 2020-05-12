package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.nio.file.Path;
import java.nio.file.Paths;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class PathStringConverter implements StringConverter<Path> {

	@Override
	public String toString(Path value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	@Override
	public Path fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			Path path = Paths.get(value);
			return path;

		} catch (Exception e) {
			throw new StringConverterException(this, value);
		}
	}

}
