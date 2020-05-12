package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.io.File;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class FileStringConverter implements StringConverter<File> {

	@Override
	public String toString(File value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	@Override
	public File fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			File file = new File(value);
			return file;

		} catch (Exception e) {
			throw new StringConverterException(this, value);
		}
	}

}
