package nth.introspect.junit.layer5provider.reflection.behavior.hidden;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class HiddenModelForActionMethodTest {

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
	public void actionMethodHiddenNotInRole() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodHiddenNotInRole");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodVisibleInRole() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodVisibleInRole");
		assertTrue(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodCollection() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodCollection");
		assertTrue(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodHiddenMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodHiddenMethod");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodVisibleMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodVisibleMethod");
		assertTrue(actionMethodInfo.isVisible(obj));
	}


	@Test
	public void actionMethodHiddenAnnotationHiddenMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodHiddenAnnotationHiddenMethod");
		assertFalse(actionMethodInfo.isVisible(obj));
	}

	@Test
	public void actionMethodHiddenVisibleMethod() {
		HiddenModelForActionMethodTestObject obj=new HiddenModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodHiddenVisibleMethod");
		assertFalse(actionMethodInfo.isVisible(obj));
	}
	
	

	private ActionMethodInfo getActionMethodInfo(String methodName) {
		List<ActionMethodInfo> actionMethodInfos = reflectionProvider.getMethodInfos(HiddenModelForActionMethodTestObject.class);
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			if (actionMethodInfo.getSimpleName().equals(methodName)) {
				return actionMethodInfo;
			}
		}
		return null;
	}

}