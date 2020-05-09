package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;

public class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = 6526318025954861050L;

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends ActionMethodResultHandler> getMultipleValueResultHandler() {
		return MultipleValueResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.UrlResultHandler> getUrlResultHandler() {
		return UrlResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.UriResultHandler> getUriResultHandler() {
		return UriResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethodexecution.result.StringConverterResultHandler> getStringConverterResultHandler() {
		return StringConverterResultHandler.class;
	}

}
