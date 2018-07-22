package nth.reflect.fw.junit.layer5provider.reflection.behavior.disabled;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class DisabledModelForActionMethodTest {

	private ClassInfo classInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit() {

			@Override
			public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
				return AcmeAuthorizationProvider.class;
			}
		};
		DependencyInjectionContainer container = application.createContainer();
		AcmeAuthorizationProvider authorizationProvider = container
				.get(AcmeAuthorizationProvider.class);
		authorizationProvider.login("carla", "password1");
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		classInfo=reflectionProvider.getClassInfo(DisabledModelForActionMethodTestObject.class);
	}

	@Test
	public void actionMethodDisabledNotInRole() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodDisabledNotInRole");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledInRole() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodEnabledInRole");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodCollection() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodCollection");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodEnabledMethod");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}


	@Test
	public void actionMethodDisabledAnnotationDisabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodDisabledAnnotationDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledEnabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=classInfo.getActionMethodInfo("actionMethodDisabledAnnotationEnabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}
	

}