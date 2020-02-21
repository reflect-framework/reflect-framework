package nth.reflect.fw.layer5provider.reflection.behavior.hidden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProviderTestObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class HiddenModelForPropertiesTest {

	private DomainClassInfo domainClassInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new TestApp();
		DependencyInjectionContainer container = application.createContainer();
		AuthorizationProviderTestObject authorizationProvider = container.get(AuthorizationProviderTestObject.class);
		authorizationProvider.login("carla", "password1");
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainClassInfo = reflectionProvider.getDomainClassInfo(HiddenModelForPropertiesTestObject.class);
	}

	private class TestApp extends ReflectApplicationForJUnit {
		@Override
		public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
			return AuthorizationProviderTestObject.class;
		}
	}

	@Test
	public void propertyHiddenInTable() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInTable");
		assertFalse(propertyInfo.isVisibleInTable());
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenInForm() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInForm");
		assertTrue(propertyInfo.isVisibleInTable());
		assertFalse(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenInFormAndTable() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInFormAndTable");
		assertFalse(propertyInfo.isVisibleInTable());
		assertFalse(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenNotInRole() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenNotInRole");
		assertFalse(propertyInfo.isVisibleInTable());
		assertFalse(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyVisibleInRole() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyVisibleInRole");
		assertTrue(propertyInfo.isVisibleInTable());
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyCollection() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyCollection");
		assertFalse(propertyInfo.isVisibleInTable());
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenMethod() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenMethod");
		assertTrue(propertyInfo.isVisibleInTable());// true: can not be set
													// dynamically
		assertFalse(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyVisibleMethod() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyVisibleMethod");
		assertTrue(propertyInfo.isVisibleInTable());// true: can not be set
													// dynamically
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenInTableHiddenInRole() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInTableHiddenInRole");
		assertFalse(propertyInfo.isVisibleInTable());
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenInTableVisibleInRole() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInTableVisibleInRole");
		assertTrue(propertyInfo.isVisibleInTable());
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenInTableHiddenMethod() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInTableHiddenMethod");
		assertFalse(propertyInfo.isVisibleInTable());
		assertFalse(propertyInfo.isVisibleInForm(obj));
	}

	@Test
	public void propertyHiddenInTableVisibleMethod() {
		HiddenModelForPropertiesTestObject obj = new HiddenModelForPropertiesTestObject();
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo("propertyHiddenInTableVisibleMethod");
		assertFalse(propertyInfo.isVisibleInTable());
		assertTrue(propertyInfo.isVisibleInForm(obj));
	}

}
