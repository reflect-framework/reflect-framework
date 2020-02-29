package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.util.Optional;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class ByteStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type==byte.class || type==Byte.class;
	}

	@Override
	public Optional<StringConverter> create(StringConverterFactoryInfo info) {
		ByteStringConverter stringConverter = new ByteStringConverter(info.getContainer(),
				info.getFormatPattern());
		return Optional.of(stringConverter);
	}

}
