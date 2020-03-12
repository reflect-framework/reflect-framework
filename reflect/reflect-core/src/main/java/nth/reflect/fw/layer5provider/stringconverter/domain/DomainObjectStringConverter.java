package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DomainObjectStringConverter extends StringConverter<Object> {

	private ReflectionProvider reflectionProvider;

	public DomainObjectStringConverter(StringConverterFactoryInfo info) {
		super(info);
		reflectionProvider = container.get(ReflectionProvider.class);
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
