package nth.introspect.junit.layer5provider.reflection;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfoComparator;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class ReflectionProviderTest {

	private static final String PROPERTY1 = "property1";
	private static final String PROPERTY2 = "property2";
	private static final String CLASS_ACTION_METHOD = "classActionMethod";
	private static final String PROPERTY1_ACTION_METHOD =  PROPERTY1+"ActionMethod";
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
	public final void testGetPropertyInfo() {
		PropertyInfo propertyInfo=reflectionProvider.getPropertyInfo(ReflectionProviderTestObject.class, PROPERTY1);
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY1, propertyInfo.getCanonicalName());
	}

	@Test
	public final void testGetPropertyInfosClassOfQ() {
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(ReflectionProviderTestObject.class);
		assertEquals(2, propertyInfos.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY1, propertyInfos.get(0).getCanonicalName());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY2, propertyInfos.get(1).getCanonicalName());
	}

	@Test
	public final void testGetPropertyInfosClassOfQFilterOfPropertyInfoComparatorOfPropertyInfo() {
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(ReflectionProviderTestObject.class, createPropertyFilter(), createPropertyComperator());
		assertEquals(1, propertyInfos.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY2, propertyInfos.get(0).getCanonicalName());
	}

	private Comparator<PropertyInfo> createPropertyComperator() {
		return new PropertyInfoComparator();
	}

	private Filter<PropertyInfo> createPropertyFilter() {
		return new Filter<PropertyInfo> () {

			@Override
			public boolean isMatch(PropertyInfo propertyInfo) {
				return propertyInfo.getSimpleName().equals(PROPERTY2);
			}
			
		};
	}

	@Test
	public final void testGetOrderedPropertyInfos() {
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(ReflectionProviderTestObject.class);
		assertEquals(2, propertyInfos.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY1, propertyInfos.get(0).getCanonicalName());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY2, propertyInfos.get(1).getCanonicalName());
	}

	@Test
	public final void testGetOrderedAndVisiblePropertyInfos() {
		List<PropertyInfo> propertyInfos = reflectionProvider.getOrderedAndVisiblePropertyInfos(ReflectionProviderTestObject.class);
		assertEquals(1, propertyInfos.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY2, propertyInfos.get(0).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQ() {
		List<ActionMethodInfo> actionMethods = reflectionProvider.getMethodInfos(ReflectionProviderTestObject.class);
		assertEquals(2, actionMethods.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+CLASS_ACTION_METHOD, actionMethods.get(0).getCanonicalName());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY1_ACTION_METHOD, actionMethods.get(1).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = reflectionProvider.getMethodInfos(ReflectionProviderTestObject.class, new MethodNameFilter(PROPERTY1_ACTION_METHOD));
		assertEquals(1, actionMethods.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()+"."+PROPERTY1_ACTION_METHOD, actionMethods.get(0).getCanonicalName());
	}

}
