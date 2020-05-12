package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class DomainObjectStringConverterFactory implements StringConverterFactory {

	private final ReflectionProvider reflectionProvider;

	public DomainObjectStringConverterFactory(ReflectionProvider reflectionProvider) {
		this.reflectionProvider = reflectionProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		return typeInfo.isDomainClass() && !typeInfo.isEnum()
				&& !TranslatableString.class.isAssignableFrom(typeInfo.getType());
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		DomainObjectStringConverter stringConverter = new DomainObjectStringConverter(reflectionProvider, typeInfo);
		return stringConverter;
	}

}
