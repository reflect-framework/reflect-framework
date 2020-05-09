package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class CharacterStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == char.class || type == Character.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		CharacterStringConverter stringConverter = new CharacterStringConverter(info);
		return stringConverter;
	}

}
