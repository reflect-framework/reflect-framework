package nth.introspect.provider.domain.info.classinfo;

import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;


public class ClassInfoFactory {

	public static ClassInfo create(DomainInfoProvider domainInfoProvider, LanguageProvider languageProvider, Class<?> domainOrServiceClass) {
		return new ClassInfo(domainInfoProvider, languageProvider, domainOrServiceClass);
	}
	
}
