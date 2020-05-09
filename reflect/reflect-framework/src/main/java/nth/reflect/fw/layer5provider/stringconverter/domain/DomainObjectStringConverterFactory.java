package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DomainObjectStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		TypeInfo typeInfo = info.getTypeInfo();
		return typeInfo.isDomainClass() && !typeInfo.isEnum()
				&& !TranslatableString.class.isAssignableFrom(typeInfo.getType());
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		DomainObjectStringConverter stringConverter = new DomainObjectStringConverter(info);
		return stringConverter;
	}

}
