package nth.reflect.fw.layer5provider.actionmethod.prehandler.impl;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * The {@link ActionMethod} parameter is edited by the user and then the
 * {@link ActionMethod} is invoked by a {@link ActionMethodInvoker} or the
 * action is canceled.
 * 
 * @author nilsth
 *
 */
public class EditDomainObject implements ActionMethodPreHandler {

	@Override
	public boolean canProcess(ActionMethodInfo methodInfo) {
		if (methodInfo.getExecutionMode() != ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL) {
			return false;
		} else {
			return methodInfo.getFirstParameterTypeInfo().isDomainClass();
		}
	}

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception {
		UserInterfaceController userInterface = container.get(UserInterfaceController.class);
		// TODO see GIT Issue#496
		methodInfo.invokeEditParameterMethod(userInterface, methodOwner, methodParameter);
	}

}
