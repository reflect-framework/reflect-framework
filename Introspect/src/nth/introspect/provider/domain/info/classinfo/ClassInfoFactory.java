package nth.introspect.provider.domain.info.classinfo;

import nth.introspect.provider.domain.info.DomainInfoProvider;


public class ClassInfoFactory {

	public static ClassInfo create(DomainInfoProvider domainInfoProvider, Class<?> domainOrServiceClass) {
		return new ClassInfo(domainInfoProvider, domainOrServiceClass);
	}
	
}
