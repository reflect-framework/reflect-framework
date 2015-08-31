package nth.introspect.junit.layer5provider.reflection;

import static org.junit.Assert.assertEquals;

import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

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


	@Test
	public final void testGetPropertyInfosClassOfQ() {
		ClassInfo classInfo = reflectionProvider.getClassInfo(ReflectionProviderTestObject.class);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		assertEquals(2, propertyInfos.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+ReflectionProviderTestObject.PROPERTY1, propertyInfos.get(0).getCanonicalName());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+ReflectionProviderTestObject.PROPERTY2, propertyInfos.get(1).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQ() {
		List<ActionMethodInfo> actionMethods = reflectionProvider.getMethodInfos(ReflectionProviderTestObject.class);
		assertEquals(2, actionMethods.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+ReflectionProviderTestObject.CLASS_ACTION_METHOD, actionMethods.get(0).getCanonicalName());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+ReflectionProviderTestObject.PROPERTY1_ACTION_METHOD, actionMethods.get(1).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = reflectionProvider.getMethodInfos(ReflectionProviderTestObject.class, new MethodNameFilter(ReflectionProviderTestObject.PROPERTY1_ACTION_METHOD));
		assertEquals(1, actionMethods.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+ReflectionProviderTestObject.PROPERTY1_ACTION_METHOD, actionMethods.get(0).getCanonicalName());
	}

}
