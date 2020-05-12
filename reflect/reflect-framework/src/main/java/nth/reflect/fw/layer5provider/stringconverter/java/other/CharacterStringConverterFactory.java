package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class CharacterStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == char.class || type == Character.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		CharacterStringConverter stringConverter = new CharacterStringConverter();
		return stringConverter;
	}

}
