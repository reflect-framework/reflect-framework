package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class DomainObjectStringConverter implements StringConverter<Object> {

	private final ReflectionProvider reflectionProvider;
	private final TypeInfo typeInfo;

	public DomainObjectStringConverter(ReflectionProvider reflectionProvider, TypeInfo typeInfo) {
		this.reflectionProvider = reflectionProvider;
		this.typeInfo = typeInfo;
	}

	@Override
	public String toString(Object domainObject) {
		if (domainObject == null) {
			return "";
		}
		Class<?> domainClass = typeInfo.getType();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		String title = domainClassInfo.getTitle(domainObject);
		return title;
	}

	@Override
	public Object fromString(String value) {
		throw new MethodNotSupportedException(DomainObjectStringConverter.class.getCanonicalName() + ".fromString()");
	}

}
