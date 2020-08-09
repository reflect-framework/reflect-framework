package nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.UploadStream;

public abstract class SelectFileToUpload implements ActionMethodPreHandler {

	/**
	 * Works for all {@link ActionMethodInfo#getExecutionMode()}'s
	 */
	@Override
	public boolean canProcess(ActionMethodInfo methodInfo) {
		Class<?> parameterType = methodInfo.getFirstParameterTypeInfo().getType();
		return UploadStream.class.isAssignableFrom(parameterType);
	}

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception {
		UploadStream uploadStream = methodParameter == null ? null : (UploadStream) methodParameter;
		preProcess(container, methodInfo, methodOwner, methodParameter, uploadStream);
	}

	protected abstract void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo,
			Object methodOwner, Object methodParameter, UploadStream uploadStream) throws Exception;

}
