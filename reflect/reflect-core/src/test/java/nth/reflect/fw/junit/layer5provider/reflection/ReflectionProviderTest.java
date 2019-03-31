package nth.reflect.fw.junit.layer5provider.reflection;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.stubs.DomainObjectStub;
import nth.reflect.fw.junit.stubs.ServiceObjectStub;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class ReflectionProviderTest {

	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		application.addServiceClass(ServiceObjectStub.class);
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
	}

	@Test
	public final void testGetApplicationClassInfos() {
		ApplicationClassInfo applicationClassInfo = reflectionProvider.getApplicationClassInfo();
		assertEquals(ReflectApplicationForJUnit.class.getCanonicalName(), applicationClassInfo.getCanonicalName());
	}

	@Test
	public final void testGetDomainClassInfo() {
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(DomainObjectStub.class);
		assertEquals(DomainObjectStub.class.getCanonicalName(), domainClassInfo.getCanonicalName());
	}

}
