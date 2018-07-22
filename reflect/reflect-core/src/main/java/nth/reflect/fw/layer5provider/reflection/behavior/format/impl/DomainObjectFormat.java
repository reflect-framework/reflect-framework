package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class DomainObjectFormat extends Format {

	private static final long serialVersionUID = -6884382558884383775L;
	private ClassInfo classInfo;

	public DomainObjectFormat(ReflectionProvider reflectionProvider, Class<?> domainClass) {
		classInfo = reflectionProvider.getClassInfo(domainClass);
	}

	@Override
	public StringBuffer format(Object domainObject, StringBuffer toAppendTo, FieldPosition pos) {
		String title = classInfo.getTitle(domainObject);
		return toAppendTo.append(title);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		throw new MethodNotSupportedException();
	}

}
