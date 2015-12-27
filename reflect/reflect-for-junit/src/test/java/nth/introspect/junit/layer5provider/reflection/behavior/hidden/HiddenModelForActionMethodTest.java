package nth.introspect.junit.layer5provider.reflection.behavior.hidden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class HiddenModelForActionMethodTest {


	private ClassInfo classInfo;

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
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		classInfo=reflectionProvider.getClassInfo(HiddenModelForActionMethodTestObject.class);
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