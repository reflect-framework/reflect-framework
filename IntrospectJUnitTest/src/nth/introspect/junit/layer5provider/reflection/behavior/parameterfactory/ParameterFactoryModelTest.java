package nth.introspect.junit.layer5provider.reflection.behavior.parameterfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class ParameterFactoryModelTest {

	private ParameterFactoryModelTestObject obj;
	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

		};
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
		obj = new ParameterFactoryModelTestObject();
	}

	@Test
	public void actionMethodWithParameterFactoryMethod()
			throws InstantiationException, IllegalAccessException {
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethod1");
		Object parameter = actionMethodInfo.createMethodParameter(obj);
		assertNotNull(parameter);
		assertEquals(parameter.getClass(),
				ParameterTestObject.class);
	}

	@Test
	public void actionMethodWithParameterFactoryAnnotation()
			throws InstantiationException, IllegalAccessException {
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethod2");
		Object parameter = actionMethodInfo.createMethodParameter(obj);
		assertNotNull(parameter);
		assertEquals(parameter.getClass(),
				ParameterTestObject.class);
	}

	private ActionMethodInfo getActionMethodInfo(String actionMethodName) {
		List<ActionMethodInfo> actionMethodInfos = reflectionProvider
				.getMethodInfos(obj.getClass());
		for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
			if (actionMethodInfo.getSimpleName().equals(actionMethodName)) {
				return actionMethodInfo;
			}
		}
		return null;
	}

}
