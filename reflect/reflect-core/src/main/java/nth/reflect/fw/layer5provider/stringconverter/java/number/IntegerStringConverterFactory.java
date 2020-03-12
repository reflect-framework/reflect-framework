package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class IntegerStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == int.class || type == Integer.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		IntegerStringConverter stringConverter = new IntegerStringConverter(info);
		return stringConverter;
	}

}
