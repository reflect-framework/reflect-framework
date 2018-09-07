package nth.reflect.fw.junit.layer5provider.reflection.behavior.hidden;

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

public class HiddenModelForActionMethodTest {


	private ClassInfo classInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new TestApp();
		DependencyInjectionContainer container = application.createContainer();
		AuthorizationProviderTestObject authorizationProvider = container
				.get(AuthorizationProviderTestObject.class);
		authorizationProvider.login("carla", "password1");
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		classInfo=reflectionProvider.getClassInfo(HiddenModelForActionMethodTestObject.class);
	}
	
	private class TestApp extends ReflectApplicationForJUnit {
		@Override
		public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
			return AuthorizationProviderTestObject.class;
		}
	}

	@Test
	public void actionMethodHiddenNotInRole() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodHiddenNotInRole");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodVisibleInRole() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodVisibleInRole");
		assertTrue(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodCollection() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodCollection");
		assertTrue(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodHiddenMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodHiddenMethod");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodVisibleMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodVisibleMethod");
		assertTrue(actionMethodInfo.isVisible(obj));
	}


	@Test
	public void actionMethodHiddenAnnotationHiddenMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodHiddenAnnotationHiddenMethod");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodHiddenVisibleMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodHiddenVisibleMethod");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

}