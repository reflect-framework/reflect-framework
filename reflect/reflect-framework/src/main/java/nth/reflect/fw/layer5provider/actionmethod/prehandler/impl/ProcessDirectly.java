package nth.reflect.fw.layer5provider.actionmethod.prehandler.impl;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ProcessDirectly implements ActionMethodPreHandler {

	@Override
	public boolean canProcess(ActionMethodInfo methodInfo) {
		return methodInfo.getExecutionMode() == ExecutionModeType.EXECUTE_METHOD_DIRECTLY;
	}

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) {
		ActionMethodInvoker invoker = new ActionMethodInvoker(container, methodInfo, methodOwner, methodParameter);
		invoker.run();
	}
}
