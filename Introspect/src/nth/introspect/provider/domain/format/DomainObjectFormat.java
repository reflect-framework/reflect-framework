package nth.introspect.provider.domain.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.util.exception.MethodNotSupportedException;

public class DomainObjectFormat extends Format {

	private static final long serialVersionUID = -6884382558884383775L;
	private ClassInfo classInfo;

	public DomainObjectFormat(DomainInfoProvider domainInfoProvider, Class<?> domainClass) {
		classInfo = domainInfoProvider.getClassInfo(domainClass);
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
