package nth.reflect.fw.junit.layer5provider.reflection.behavior.disabled;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.layer5provider.authorization.AuthorizationProviderTestObject;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class DisabledModelForActionMethodTest {

	private ClassInfo classInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new TestApp();
		DependencyInjectionContainer container = application.createContainer();
		AuthorizationProviderTestObject authorizationProvider = container.get(AuthorizationProviderTestObject.class);
		authorizationProvider.login("carla", "password1");
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		classInfo = reflectionProvider.getClassInfo(DisabledModelForActionMethodTestObject.class);
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
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodDisabledNotInRole");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledInRole() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodEnabledInRole");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodCollection() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodCollection");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodEnabledMethod");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledAnnotationDisabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = classInfo
				.getActionMethodInfo("actionMethodDisabledAnnotationDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledEnabledMethod() {
		DisabledModelForActionMethodTestObject obj = new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo = classInfo
				.getActionMethodInfo("actionMethodDisabledAnnotationEnabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

}