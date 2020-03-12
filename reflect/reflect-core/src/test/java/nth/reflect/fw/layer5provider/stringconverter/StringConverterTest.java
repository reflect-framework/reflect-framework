package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

/**
 * Abstract test class for convinience
 * 
 * @author nilsth
 *
 */
public abstract class StringConverterTest {

	private DependencyInjectionContainer container;
	public final static String BOGUS = "Bogus";
	public final static String EMPTY = "";
	public final static String SPACE = " ";

	public DependencyInjectionContainer getContainer() {
		if (container == null) {
			container = new ReflectApplicationForJUnit().createContainer();
		}
		return container;
	}

	public StringConverterFactoryInfo createInfo(String domainObjectGetterMethod) {
		StringConverterFactoryInfo info = InfoFactory.create(getContainer(), domainObjectGetterMethod);
		return info;
	}

	public StringConverterFactoryInfo createInfo(String domainObjectGetterMethod, String formatPattern) {
		StringConverterFactoryInfo info = InfoFactory.create(getContainer(), domainObjectGetterMethod, formatPattern);
		return info;
	}

}
