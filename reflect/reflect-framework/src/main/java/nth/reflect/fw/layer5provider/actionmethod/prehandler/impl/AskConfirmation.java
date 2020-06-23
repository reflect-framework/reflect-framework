package nth.reflect.fw.layer5provider.actionmethod.prehandler.impl;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;

public class AskConfirmation implements ActionMethodPreHandler {

	private final StringConverterProvider stringConverterProvider;

	public AskConfirmation(StringConverterProvider stringConverterProvider) {
		this.stringConverterProvider = stringConverterProvider;
	}

	@Override
	public boolean canProcess(ActionMethodInfo methodInfo) {
		if (ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION != methodInfo.getExecutionMode()) {
			return false;
		} else {
			TypeInfo parameterTypeInfo = methodInfo.getFirstParameterTypeInfo();
			boolean supportedByStringConverter = stringConverterProvider.canCreate(parameterTypeInfo);
			return supportedByStringConverter;
		}
	}

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception {
		UserInterfaceController userInterface = container.get(UserInterfaceController.class);
		// TODO see GIT Issue#497
		methodInfo.invokeConfirmMethod(userInterface, methodOwner, methodParameter);
	}

}
