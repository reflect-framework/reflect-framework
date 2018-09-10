package nth.reflect.fw.junit.layer5provider.reflection;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class ReflectionProviderTest {

	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider=container.get(ReflectionProvider.class);
	}

	@Test
	public final void testGetClassInfo() {
		ClassInfo classInfo = reflectionProvider.getClassInfo(ReflectionProviderTestObject.class);
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName(), classInfo.getCanonicalName());
	}

}
