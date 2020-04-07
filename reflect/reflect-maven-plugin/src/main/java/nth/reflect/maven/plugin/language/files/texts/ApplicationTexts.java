package nth.reflect.maven.plugin.language.files.texts;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;

public class ApplicationTexts extends Texts {
	private static final long serialVersionUID = -1559927700176968624L;

	public ApplicationTexts(DependencyInjectionContainer container) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		ApplicationClassInfo applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		put(applicationClassInfo);
		Class<?> applicationClass = applicationClassInfo.getType();
		putPropertiesFromTranslatableStringsFromStaticFields(applicationClass);
	}
}
