package nth.reflect.fw.ui.commandline.layer5provider.actionmethod.result;

import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;

public class ActionMethodResultHandlerClasses
		extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = 6526318025954861050L;

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends ActionMethodResultHandler> getMultipleValueResultHandler() {
		return MultipleValueResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler> getUrlResultHandler() {
		return UrlResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UriResultHandler> getUriResultHandler() {
		return UriResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.StringConverterResultHandler> getStringConverterResultHandler() {
		return StringConverterResultHandler.class;
	}

}
