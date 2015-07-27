package nth.introspect.layer5provider.domain.info.classinfo;

import nth.introspect.layer5provider.domain.info.DomainInfoProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;


public class ClassInfoFactory {

	public static ClassInfo create(DomainInfoProvider domainInfoProvider, PathProvider pathprovider, LanguageProvider languageProvider, Class<?> domainOrServiceClass) {
		return new ClassInfo(domainInfoProvider, pathprovider, languageProvider, domainOrServiceClass);
	}
	
}
