package nth.introspect.layer5provider.reflection.info.classinfo;

import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;


public class ClassInfoFactory {

	public static ClassInfo create(ReflectionProvider reflectionProvider, PathProvider pathprovider, LanguageProvider languageProvider, Class<?> domainOrServiceClass) {
		return new ClassInfo(reflectionProvider, pathprovider, languageProvider, domainOrServiceClass);
	}
	
}
