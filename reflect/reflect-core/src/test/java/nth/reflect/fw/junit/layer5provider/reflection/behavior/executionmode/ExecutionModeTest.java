package nth.reflect.fw.junit.layer5provider.reflection.behavior.executionmode;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;

public class ExecutionModeTest {

	private ClassInfo classInfo;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		classInfo=reflectionProvider.getClassInfo(ExecutionModeTestObject.class);
	}

	@Test
	public void actionMethodDefaultExecutionMode() {
		ActionMethodInfo actionMethodInfo = classInfo.getActionMethodInfo("actionMethodDefaultExecutionMode");
		ExecutionModeType executionMode = actionMethodInfo.getExecutionMode();
		assertEquals(
				ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL,
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
