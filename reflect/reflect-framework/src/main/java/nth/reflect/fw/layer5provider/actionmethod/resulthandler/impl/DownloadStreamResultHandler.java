package nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.DownloadStream;

public abstract class DownloadStreamResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		Class<?> methodReturnType = methodInfo.getReturnTypeInfo().getType();
		boolean isDownloadStream = DownloadStream.class.isAssignableFrom(methodReturnType);
		return isDownloadStream;
	}

}
