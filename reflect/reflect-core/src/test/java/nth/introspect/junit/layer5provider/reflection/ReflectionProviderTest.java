package nth.introspect.junit.layer5provider.reflection;

import static org.junit.Assert.assertEquals;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.junit.IntrospectApplicationForJUnit;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

import org.junit.Before;
import org.junit.Test;

public class ReflectionProviderTest {

	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider=container.get(ReflectionProvider.class);
	}

	@Test
	public final void testGetClassInfo() {
		ClassInfo classInfo = reflectionProvider.getClassInfo(ReflectionProviderTestObject.class);
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName(), classInfo.getCanonicalName());
	}

}
