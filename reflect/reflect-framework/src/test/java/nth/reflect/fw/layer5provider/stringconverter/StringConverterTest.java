package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * Abstract test class for convinience
 * 
 * @author nilsth
 *
 */
public abstract class StringConverterTest {

	private DependencyInjectionContainer container;
	private ReflectionProvider reflectionProvider;
	private LanguageProvider languageProvider;
	private ReflectApplication application;
	public final static String BOGUS = "Bogus";
	public final static String EMPTY = "";
	public final static String SPACE = " ";
	public final static String NO_FORMAT = null;

	public DependencyInjectionContainer getContainer() {
		if (container == null) {
			container = new ReflectApplicationForJUnit().createContainer();
		}
		return container;
	}

	public ReflectionProvider getReflectionProvider() {
		if (reflectionProvider == null) {
			reflectionProvider = getContainer().get(ReflectionProvider.class);
		}
		return reflectionProvider;
	}

	public LanguageProvider getLanguageProvider() {
		if (languageProvider == null) {
			languageProvider = getContainer().get(LanguageProvider.class);
		}
		return languageProvider;
	}

	protected ReflectApplication getApplication() {
		if (application == null) {
			application = getContainer().get(ReflectApplication.class);
		}
		return application;
	}

	public TypeInfo createTypeInfo(String domainObjectGetterMethod) {
		TypeInfo info = InfoFactory.create(getApplication(), domainObjectGetterMethod);
		return info;
	}

	public TypeInfo createTypeInfo(String domainObjectGetterMethod, String formatPattern) {
		TypeInfo info = InfoFactory.create(getApplication(), domainObjectGetterMethod, formatPattern);
		return info;
	}

}
