package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class StringStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == String.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		StringStringConverter stringConverter = new StringStringConverter();
		return stringConverter;
	}

}
