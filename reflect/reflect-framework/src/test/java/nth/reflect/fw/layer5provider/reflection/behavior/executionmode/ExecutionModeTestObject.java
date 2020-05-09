package nth.reflect.fw.layer5provider.reflection.behavior.executionmode;

import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterTestObject;

public class ExecutionModeTestObject {

	public void actionMethodDefaultExecutionMode(ParameterTestObject obj) {
	}
	
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void actionMethodWithAnnotation(ParameterTestObject obj) {
	}
	
	@ExecutionMode(mode=ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL)
	public void actionMethodWithoutParameterNotEditable() {
	}
	
	public void actionMethodWithoutParameter() {
	}
	
	@ExecutionMode(mode=ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void actionMethodWithoutParameterNeedingConformation() {
	}
}
