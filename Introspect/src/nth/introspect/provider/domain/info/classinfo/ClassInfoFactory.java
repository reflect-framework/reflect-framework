package nth.introspect.provider.domain.info.classinfo;

import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;


public class ClassInfoFactory {

	public static ClassInfo create(DomainInfoProvider domainInfoProvider, PathProvider pathprovider, LanguageProvider languageProvider, Class<?> domainOrServiceClass) {
		return new ClassInfo(domainInfoProvider, pathprovider, languageProvider, domainOrServiceClass);
	}
	
}
