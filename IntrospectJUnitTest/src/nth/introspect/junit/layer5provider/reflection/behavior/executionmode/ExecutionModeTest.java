package nth.introspect.junit.layer5provider.reflection.behavior.executionmode;

import static org.junit.Assert.assertEquals;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.junit.IntrospectApplicationForJUnit;

import org.junit.Before;
import org.junit.Test;

public class ExecutionModeTest {

	private ExecutionModeTestObject obj;
	private ClassInfo classInfo;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {

		};
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		classInfo=reflectionProvider.getClassInfo(ExecutionModeTestObject.class);
		obj = new ExecutionModeTestObject();
	}

	@Test
	public void actionMethodDefaultExecutionMode() {
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodDefaultExecutionMode");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(
				ExecutionModeType.EDIT_PARAMETER_THAN_EXECUTE_METHOD_OR_CANCEL,
				executionMode);
	}

	@Test
	public void actionMethodWithAnnotation() {
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodWithAnnotation");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION,
				executionMode);
	}

	@Test
	public void actionMethodWithoutParameterNotEditable() {
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodWithoutParameterNotEditable");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_DIRECTLY,
				executionMode); 
	}
	
	@Test
	public void actionMethodWithoutParameter() {
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodWithoutParameter");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_DIRECTLY,
				executionMode);
	}

	@Test
	public void actionMethodWithoutParameterNeedingConformation(){
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodWithoutParameterNeedingConformation");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION,
				executionMode);
	}


}
