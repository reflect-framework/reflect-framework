package nth.reflect.fw.ui.commandline.layer5provider.actionmethod.execution;

import java.lang.reflect.InvocationTargetException;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionMethodExecutionProvider
		implements nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	@Override
	public void execute(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		Object methodReturnValue;
		try {
			methodReturnValue = methodInfo.invoke(methodOwner, methodParameter);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		// show method result
		methodInfo.processResult(container, methodOwner, methodParameter, methodReturnValue);
	}

}
