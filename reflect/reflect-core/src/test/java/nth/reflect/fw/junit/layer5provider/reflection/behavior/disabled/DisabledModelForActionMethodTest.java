package nth.reflect.fw.junit.layer5provider.reflection.behavior.disabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.layer5provider.authorization.AuthorizationProviderTestObject;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class DisabledModelForActionMethodTest {

	private DomainClassInfo domainClassInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new TestApp();
		DependencyInjectionContainer container = application.createContainer();
		AuthorizationProviderTestObject authorizationProvider = container.get(AuthorizationProviderTestObject.class);
		authorizationProvider.login("carla", "password1");
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainClassInfo = reflectionProvider.getDomainClassInfo(DisabledModelForActionMethodTestObject.class);
	}

	private class TestApp extends ReflectApplicationForJUnit {
		@Override
		public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
			return AuthorizationProviderTestObject.class;
		}
	};

	@Test
	public void actionMethodDisabledNotInRole() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethodDisabledNotInRole");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledInRole() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethodEnabledInRole");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodCollection() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethodCollection");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethodDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo.getActionMethodInfo("actionMethodEnabledMethod");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledAnnotationDisabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo
				.getActionMethodInfo("actionMethodDisabledAnnotationDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledEnabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = domainClassInfo
				.getActionMethodInfo("actionMethodDisabledAnnotationEnabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

}