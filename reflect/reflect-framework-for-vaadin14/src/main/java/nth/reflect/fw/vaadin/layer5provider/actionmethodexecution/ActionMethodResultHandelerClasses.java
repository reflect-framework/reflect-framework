package nth.reflect.fw.vaadin.layer5provider.actionmethodexecution;

public class ActionMethodResultHandelerClasses
		extends nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.ActionMethodResultHandlerClasses {

	private static final long serialVersionUID = 2547177520256232384L;

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.DomainObjectResultHandler> getDomainObjectResultHandler() {
		return DomainObjectResultHandler.class;
	}

	@Override
	public Class<? extends nth.reflect.fw.gui.layer5provider.actionmethodexecution.result.MultipleValueResultHandler> getMultipleValueResultHandler() {
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
	public Class<? extends nth.reflect.fw.layer5provider.actionmethod.result.handler.DownloadStreamResultHandler> getDownloadStreamResultHandler() {
		return DownloadStreamResultHandler.class;
	}

}
