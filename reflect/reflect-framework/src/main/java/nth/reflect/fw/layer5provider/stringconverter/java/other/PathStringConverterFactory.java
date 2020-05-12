package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.nio.file.Path;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class PathStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return Path.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		PathStringConverter stringConverter = new PathStringConverter();
		return stringConverter;
	}

}
