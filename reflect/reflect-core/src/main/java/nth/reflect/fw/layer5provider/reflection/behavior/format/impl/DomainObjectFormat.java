package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class DomainObjectFormat extends Format {

	private static final long serialVersionUID = -6884382558884383775L;
	private final ReflectionProvider reflectionProvider;
	private final Class<?> domainClass;

	public DomainObjectFormat(ReflectionProvider reflectionProvider, Class<?> domainClass) {
		this.reflectionProvider = reflectionProvider;
		this.domainClass = domainClass;
	}

	@Override
	public StringBuffer format(Object domainObject, StringBuffer toAppendTo, FieldPosition pos) {
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		String title = domainClassInfo.getTitle(domainObject);
		return toAppendTo.append(title);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		throw new MethodNotSupportedException(DomainObjectFormat.class.getCanonicalName() + ".parseObject()");
	}

}
