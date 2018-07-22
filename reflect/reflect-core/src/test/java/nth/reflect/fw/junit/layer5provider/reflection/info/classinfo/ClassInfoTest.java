package nth.reflect.fw.junit.layer5provider.reflection.info.classinfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.layer5provider.reflection.ReflectionProviderTestObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

import org.junit.Before;
import org.junit.Test;

public class ClassInfoTest {

	private ClassInfo classInfo;
	private Class<ReflectionProviderTestObject> objectClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container
				.get(ReflectionProvider.class);
		objectClass = ReflectionProviderTestObject.class;
		classInfo = reflectionProvider.getClassInfo(objectClass);
	}

	@Test
	public void testGetSimpleName() {
		assertEquals(objectClass.getSimpleName(), classInfo.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertEquals(objectClass.getCanonicalName(),
				classInfo.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertEquals(objectClass, classInfo.getObjectClass());
	}

	@Test
	public void testGetDisplayName() {
		assertEquals(
				StringUtil.convertToNormalCase(objectClass.getSimpleName()),
				classInfo.getDisplayName());
	}

	@Test
	public void testGetDescription() {
		assertEquals(ReflectionProviderTestObject.DESCRIPTION,
				classInfo.getDescription());
	}

	@Test
	public void testGetIconURI() {
		assertNull(classInfo.getIconURL(new ReflectionProviderTestObject()));
	}

	@Test
	public void testGetTitle() {
		assertEquals(ReflectionProviderTestObject.TITLE,
				classInfo.getTitle(new ReflectionProviderTestObject()));
	}

	@Test
	public void testToString() {
		assertEquals(objectClass.getCanonicalName(), classInfo.toString());
	}

	@Test
	public void testGetAllValidationMethods() {
		assertEquals(0, classInfo.getAllValidationMethods().size());
	}

	@Test
	public void testGetPropertyInfosSorted() {
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		assertEquals(2, propertyInfos.size());
//		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
//				+ "." + ReflectionProviderTestObject.PROPERTY1, propertyInfos
//				.get(0).getCanonicalName());
//		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
//				+ "." + ReflectionProviderTestObject.PROPERTY2, propertyInfos
//				.get(1).getCanonicalName());
	}

	@Test
	public void testGetPropertyInfo() {
		PropertyInfo propertyInfo = classInfo
				.getPropertyInfo(ReflectionProviderTestObject.PROPERTY1);
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
				+ "." + ReflectionProviderTestObject.PROPERTY1,
				propertyInfo.getCanonicalName());
	}

	@Test
	public final void testGetPropertyInfosClassOfQ() {
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		assertEquals(2, propertyInfos.size());
//		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
//				+ "." + ReflectionProviderTestObject.PROPERTY1, propertyInfos
//				.get(0).getCanonicalName());
//		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
//				+ "." + ReflectionProviderTestObject.PROPERTY2, propertyInfos
//				.get(1).getCanonicalName());
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = classInfo
				.getActionMethodInfosSorted();
		assertEquals(2, actionMethods.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
				+ "." + ReflectionProviderTestObject.CLASS_ACTION_METHOD,
				actionMethods.get(0).getCanonicalName());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
				+ "." + ReflectionProviderTestObject.PROPERTY1_ACTION_METHOD,
				actionMethods.get(1).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = classInfo.getActionMethodInfos(
				 new MethodNameFilter(
						ReflectionProviderTestObject.PROPERTY1_ACTION_METHOD));
		assertEquals(1, actionMethods.size());
		assertEquals(ReflectionProviderTestObject.class.getCanonicalName()
				+ "." + ReflectionProviderTestObject.PROPERTY1_ACTION_METHOD,
				actionMethods.get(0).getCanonicalName());
	}

}
