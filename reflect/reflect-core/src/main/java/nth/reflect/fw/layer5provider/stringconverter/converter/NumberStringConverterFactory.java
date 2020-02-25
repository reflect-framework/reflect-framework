package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.util.Optional;

import nth.reflect.fw.layer5provider.stringconverter.abstractconverter.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.abstractconverter.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.abstractconverter.StringConverterFactoryInfo;

public class NumberStringConverterFactory implements  StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		//TODO does this include primitive types???? otherwise use PrimitiveType.primitiveToWrapper(typeInfo.getType())
		return Number.class.isAssignableFrom(info.getTypeInfo().getType());
	}

	@Override
		public Optional<StringConverter> create(StringConverterFactoryInfo info) {
		NumberStringConverter numberStringConverter = new NumberStringConverter(info.getContainer(), info.getFormatPattern());
		return Optional.of(numberStringConverter);
	}

}
