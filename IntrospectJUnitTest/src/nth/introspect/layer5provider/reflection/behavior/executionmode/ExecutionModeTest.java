package nth.introspect.layer5provider.reflection.behavior.executionmode;

import static org.junit.Assert.assertEquals;

import java.util.List;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class ExecutionModeTest {

	private ExecutionModeTestObject obj;
	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

		};
		DependencyInjectionContainer container = application.createContainer();
		reflectionProvider = container.get(ReflectionProvider.class);
		obj = new ExecutionModeTestObject();
	}

	@Test
	public void actionMethodDefaultExecutionMode() {
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethodDefaultExecutionMode");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(
				ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL,
				executionMode);
	}

	@Test
	public void actionMethodWithAnnotation() {
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethodWithAnnotation");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION,
				executionMode);
	}

	@Test
	public void actionMethodWithoutParameterNotEditable() {
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethodWithoutParameterNotEditable");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_DIRECTLY,
				executionMode); 
	}
	
	@Test
	public void actionMethodWithoutParameter() {
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethodWithoutParameter");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_DIRECTLY,
				executionMode);
	}

	@Test
	public void actionMethodWithoutParameterNeedingConformation(){
		ActionMethodInfo actionMethodInfo = getActionMethodInfo("actionMethodWithoutParameterNeedingConformation");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION,
				executionMode);
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
