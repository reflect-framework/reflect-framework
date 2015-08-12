package nth.introspect.junit.layer5provider.reflection.behavior.disabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class DisabledModelForPropertiesTest {

	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

			@Override
			public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
				return AcmeAuthorizationProvider.class;
			}
		};
		DependencyInjectionContainer container = application.createContainer();
		AcmeAuthorizationProvider authorizationProvider = container
				.get(AcmeAuthorizationProvider.class);
		authorizationProvider.login("carla", "password1");
		reflectionProvider = container.get(ReflectionProvider.class);
	}


	@Test
	public void propertyDisabled() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyDisabled");
		assertFalse(propertyInfo.isEnabled(obj));

	}


	@Test
	public void propertyEnabled() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyEnabled");
		assertTrue(propertyInfo.isEnabled(obj));

	}
	
	@Test
	public void propertyDisabledNotInRole() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyDisabledNotInRole");
		assertFalse(propertyInfo.isEnabled(obj));
	}

	@Test
	public void propertyEnabledInRole() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyEnabledInRole");
		assertTrue(propertyInfo.isEnabled(obj));

	}

	@Test
	public void propertyCollection() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyCollection");
		assertTrue(propertyInfo.isEnabled(obj));
	}

	@Test
	public void propertyDisabledMethod() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class,"propertyDisabledMethod" );
		assertFalse(propertyInfo.isEnabled(obj));

	}

	@Test
	public void propertyEnabledMethod() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyEnabledMethod");
		assertTrue(propertyInfo.isEnabled(obj));
	}

	@Test
	public void propertyDisabledAnnotationDisabledMethod() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class,"propertyDisabledAnnotationDisabledMethod" );
		assertFalse(propertyInfo.isEnabled(obj));
	}

	@Test
	public void propertyDisabledAnnotationEnabledMethod() {
		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
		PropertyInfo propertyInfo = reflectionProvider.getPropertyInfo(
				DisabledModelForPropertiesTestObject.class, "propertyDisabledAnnotationEnabledMethod");
		assertFalse(propertyInfo.isEnabled(obj));
	}
}
