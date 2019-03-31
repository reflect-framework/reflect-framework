package nth.reflect.fw.junit.layer5provider.reflection.info.classinfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.stubs.DomainObjectStub;
import nth.reflect.fw.junit.stubs.ServiceObjectStub;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class ServiceClassInfoTest {

	private ServiceClassInfo serviceClassInfo;
	private Class<?> serviceClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		application.addServiceClass(ServiceObjectStub.class);
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceClassInfo = reflectionProvider.getServiceClassInfo(ServiceObjectStub.class);
		serviceClass = serviceClassInfo.getObjectClass();
	}

	@Test
	public void testGetSimpleName() {
		assertEquals(serviceClass.getSimpleName(), serviceClassInfo.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertEquals(serviceClass.getCanonicalName(), serviceClassInfo.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertEquals(serviceClass, serviceClassInfo.getObjectClass());
	}

	@Test
	public void testGetDisplayName() {
		assertEquals(StringUtil.convertToNormalCase(serviceClass.getSimpleName()), serviceClassInfo.getDisplayName());
	}

	@Test
	public void testGetDescription() {
		assertEquals(ServiceObjectStub.DESCRIPTION, serviceClassInfo.getDescription());
	}

	@Test
	public void testGetIconURI() {
		assertNull(serviceClassInfo.getFontIconUrl(new DomainObjectStub()));
	}

	@Test
	public void testGetTitle() {
		assertEquals(DomainObjectStub.TITLE, serviceClassInfo.getTitle(new DomainObjectStub()));
	}

	@Test
	public void testToString() {
		assertEquals(serviceClass.getCanonicalName(), serviceClassInfo.toString());
	}

	@Test
	public void testGetAllValidationMethods() {
		assertEquals(0, serviceClassInfo.getAllValidationMethods().size());
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = serviceClassInfo.getActionMethodInfosSorted();
		assertEquals(1, actionMethods.size());
		assertEquals(ServiceObjectStub.class.getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME,
				actionMethods.get(0).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = serviceClassInfo
				.getActionMethodInfos(new MethodNameFilter(ServiceObjectStub.ACTION_METHOD_NAME));
		assertEquals(1, actionMethods.size());
		assertEquals(ServiceObjectStub.class.getCanonicalName() + "." + ServiceObjectStub.ACTION_METHOD_NAME,
				actionMethods.get(0).getCanonicalName());
	}

}
