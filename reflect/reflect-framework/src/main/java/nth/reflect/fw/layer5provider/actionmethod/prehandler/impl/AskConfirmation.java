package nth.reflect.fw.layer5provider.actionmethod.prehandler.impl;

import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;

public abstract class AskConfirmation implements ActionMethodPreHandler {

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

}
