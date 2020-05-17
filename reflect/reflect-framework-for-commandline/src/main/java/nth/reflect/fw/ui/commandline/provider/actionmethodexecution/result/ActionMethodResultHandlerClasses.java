package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandler;

public class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = 6526318025954861050L;

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.result.handler.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends ActionMethodResultHandler> getMultipleValueResultHandler() {
		return MultipleValueResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.result.handler.UrlResultHandler> getUrlResultHandler() {
		return UrlResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.result.handler.UriResultHandler> getUriResultHandler() {
		return UriResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.result.handler.StringConverterResultHandler> getStringConverterResultHandler() {
		return StringConverterResultHandler.class;
	}

}
