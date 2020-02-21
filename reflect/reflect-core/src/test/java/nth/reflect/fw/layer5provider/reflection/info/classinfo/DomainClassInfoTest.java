package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.stubs.DomainObjectStub;

public class DomainClassInfoTest {

	private DomainClassInfo domainClassInfo;
	private Class<DomainObjectStub> domainObjectClass;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectClass = DomainObjectStub.class;
		domainClassInfo = reflectionProvider.getDomainClassInfo(domainObjectClass);
	}

	@Test
	public void testGetSimpleName() {
		assertEquals(domainObjectClass.getSimpleName(), domainClassInfo.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() {
		assertEquals(domainObjectClass.getCanonicalName(), domainClassInfo.getCanonicalName());
	}

	@Test
	public void testGetObjectClass() {
		assertEquals(domainObjectClass, domainClassInfo.getObjectClass());
	}

	@Test
	public void testGetDisplayName() {
		assertEquals(StringUtil.convertToNormalCase(domainObjectClass.getSimpleName()),
				domainClassInfo.getDisplayName());
	}

	@Test
	public void testGetDescription() {
		assertEquals(DomainObjectStub.DESCRIPTION, domainClassInfo.getDescription());
	}

	@Test
	public void testGetIconURI() {
		assertNull(domainClassInfo.getFontIconUrl(new DomainObjectStub()));
	}

	@Test
	public void testGetTitle() {
		assertEquals(DomainObjectStub.TITLE, domainClassInfo.getTitle(new DomainObjectStub()));
	}

	@Test
	public void testToString() {
		assertEquals(domainObjectClass.getCanonicalName(), domainClassInfo.toString());
	}

	@Test
	public void testGetAllValidationMethods() {
		assertEquals(0, domainClassInfo.getAllValidationMethods().size());
	}

	@Test
	public void testGetPropertyInfosSorted() {
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		assertEquals(2, propertyInfos.size());
		// assertEquals(DomainObjectStub.class.getCanonicalName()
		// + "." + DomainObjectStub.PROPERTY1, propertyInfos
		// .get(0).getCanonicalName());
		// assertEquals(DomainObjectStub.class.getCanonicalName()
		// + "." + DomainObjectStub.PROPERTY2, propertyInfos
		// .get(1).getCanonicalName());
	}

	@Test
	public void testGetPropertyInfo() {
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo(DomainObjectStub.PROPERTY1);
		assertEquals(DomainObjectStub.class.getCanonicalName() + "." + DomainObjectStub.PROPERTY1,
				propertyInfo.getCanonicalName());
	}

	@Test
	public final void testGetPropertyInfosClassOfQ() {
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		assertEquals(2, propertyInfos.size());
		// assertEquals(DomainObjectStub.class.getCanonicalName()
		// + "." + DomainObjectStub.PROPERTY1, propertyInfos
		// .get(0).getCanonicalName());
		// assertEquals(DomainObjectStub.class.getCanonicalName()
		// + "." + DomainObjectStub.PROPERTY2, propertyInfos
		// .get(1).getCanonicalName());
	}

	@Test
	public void testGetActionMethodSorted() {
		List<ActionMethodInfo> actionMethods = domainClassInfo.getActionMethodInfosSorted();
		assertEquals(1, actionMethods.size());
		assertEquals(DomainObjectStub.class.getCanonicalName() + "." + DomainObjectStub.CLASS_ACTION_METHOD,
				actionMethods.get(0).getCanonicalName());
//		assertEquals(
//				DomainObjectStub.class.getCanonicalName() + "."
//						+ DomainObjectStub.PROPERTY1_ACTION_METHOD,
//				actionMethods.get(1).getCanonicalName());
	}

	@Test
	public final void testGetMethodInfosClassOfQFilterOfActionMethodInfo() {
		List<ActionMethodInfo> actionMethods = domainClassInfo
				.getActionMethodInfos(new MethodNameFilter(DomainObjectStub.CLASS_ACTION_METHOD));
		assertEquals(1, actionMethods.size());
		assertEquals(DomainObjectStub.class.getCanonicalName() + "." + DomainObjectStub.CLASS_ACTION_METHOD,
				actionMethods.get(0).getCanonicalName());
	}

}
