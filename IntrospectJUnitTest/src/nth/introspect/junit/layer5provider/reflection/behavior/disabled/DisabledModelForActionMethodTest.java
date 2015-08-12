package nth.introspect.junit.layer5provider.reflection.behavior.disabled;

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

public class DisabledModelForActionMethodTest {

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
	public void actionMethodDisabledNotInRole() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodDisabledNotInRole");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledInRole() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodEnabledInRole");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodCollection() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodCollection");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodEnabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodEnabledMethod");
		assertTrue(actionMethodInfo.isEnabled(obj));
	}


	@Test
	public void actionMethodDisabledAnnotationDisabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodDisabledAnnotationDisabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}

	@Test
	public void actionMethodDisabledEnabledMethod() {
		DisabledModelForActionMethodTestObject obj=new DisabledModelForActionMethodTestObject();
		ActionMethodInfo actionMethodInfo=getActionMethodInfo("actionMethodDisabledAnnotationEnabledMethod");
		assertFalse(actionMethodInfo.isEnabled(obj));
	}
	
	

	private ActionMethodInfo getActionMethodInfo(String methodName) {
		List<ActionMethodInfo> actionMethodInfos = reflectionProvider.getMethodInfos(DisabledModelForActionMethodTestObject.class);
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			if (actionMethodInfo.getSimpleName().equals(methodName)) {
				return actionMethodInfo;
			}
		}
		return null;
	}

}